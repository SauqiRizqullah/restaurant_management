package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.request.AuthRequest;
import com.enigma.challengeshop.dto.response.LoginResponse;
import com.enigma.challengeshop.dto.response.RegisterResponse;

public interface AuthService {
    //1. Melakukan Pendaftaran Akun
    RegisterResponse register(AuthRequest request);

    //2. Melakukan Pendaftaran Akun Role Admin
    RegisterResponse registerAdmin(AuthRequest request);

    //3. Melakukan Login
    LoginResponse login(AuthRequest request);

}
