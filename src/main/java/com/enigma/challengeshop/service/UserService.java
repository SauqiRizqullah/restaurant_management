package com.enigma.challengeshop.service;

import com.enigma.challengeshop.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserAccount getByUserId(String id); //mencari user

    UserAccount getByContext();

}
