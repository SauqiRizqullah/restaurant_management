package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.constant.UserRole;
import com.enigma.challengeshop.dto.request.AuthRequest;
import com.enigma.challengeshop.dto.response.LoginResponse;
import com.enigma.challengeshop.dto.response.RegisterResponse;
import com.enigma.challengeshop.entity.Customer;
import com.enigma.challengeshop.entity.Role;
import com.enigma.challengeshop.entity.UserAccount;
import com.enigma.challengeshop.repository.UserAccountRepository;
import com.enigma.challengeshop.service.AuthService;
import com.enigma.challengeshop.service.CustomerService;
import com.enigma.challengeshop.service.JwtService;
import com.enigma.challengeshop.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

//    shop.superadmin.username=${USERNAME_SUPER_ADMIN:superadmin}
//    shop.superadmin.password=${PASSWORD_SUPER_ADMIN:password}

    @Value("${shop.superadmin.username}")
    private String superAdminUsername;

    @Value("${shop.superadmin.password}")
    private String superAdminPassword;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initSuperAdmin(){
        Optional<UserAccount> currentUser = userAccountRepository.findByUsername(superAdminUsername);
        if (currentUser.isPresent()) {
            return; // kalau ada di return aja
        }

        Role superAdmin = roleService.getOrSave(UserRole.ROLE_SUPER_ADMIN);
        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        UserAccount account = UserAccount.builder()
                .username(superAdminUsername)
                .password(passwordEncoder.encode(superAdminPassword))
                .role(List.of(superAdmin,admin,customer))
                .isEnable(true)
                .build();
        userAccountRepository.save(account);

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) throws DataIntegrityViolationException {
        Role role = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        // ngebuat role
        // disni saat regiter kita buat rolenya, kalau belum ada, rolenya dibuat, nah dikasus ini berarti rolenya ROLE_CUSTOMER, lalu kalau sudah ada, jangan buat lagi, berarti kita gunakan

        String hashPassword = passwordEncoder.encode(request.getPassword());

        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .role(List.of(role))
                .isEnable(true)
                .build();

        userAccountRepository.saveAndFlush(account);
        Customer customer = Customer.builder()
                .isMember(true)
                .userAccount(account)
                .build();

        customerService.createForUserAccount(customer);


        return RegisterResponse.builder()
                .username(account.getUsername())
//						.roles(account.getRole().stream().map(rl -> rl.getRole().toString()).toList())
//						.roles(account.getAuthorities().stream().map(grantedAuthority  -> grantedAuthority.getAuthority()).toList())
//						.roles(account.getAuthorities().stream().map(grantedAuthority =  new GrantedAuthority -> grantedAuthority.getAuthority()).toList())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(AuthRequest request) {

        // mendaftarkan username dan password untuk authentikasi
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        // Pengecekan username dan password di database
        Authentication authenticated = authenticationManager.authenticate(authentication);

        UserAccount userAccount = (UserAccount) authenticated.getPrincipal();
        String token = jwtService.generateToken(userAccount);
        return LoginResponse.builder()
                .token(token)
                .username(userAccount.getUsername())
                .roles(userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();

    }

    // Cari tau error  pada saat run aplikasi
    // cari tau gimana bisa dapat jwt secret key dari application properties
}
