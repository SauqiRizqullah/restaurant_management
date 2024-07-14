package com.enigma.challengeshop.service.impl;

import com.enigma.challengeshop.constant.UserRole;
import com.enigma.challengeshop.entity.Role;
import com.enigma.challengeshop.repository.RoleRepository;
import com.enigma.challengeshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(UserRole role) {
        //
        // ngebuat role
        // disni saat regiter kita buat rolenya, kalau belum ada, rolenya dibuat, nah dikasus ini berarti rolenya ROLE_CUSTOMER, lalu kalau sudah ada, jangan buat lagi, berarti kita gunakan

        return roleRepository.findByRole(role)
                .orElseGet(() -> roleRepository.saveAndFlush(
                        Role.builder()
                                .role(role)
                                .build()
                ));

    }
}
