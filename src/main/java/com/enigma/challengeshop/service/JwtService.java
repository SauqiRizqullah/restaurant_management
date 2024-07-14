package com.enigma.challengeshop.service;

import com.enigma.challengeshop.dto.response.JwtClaims;
import com.enigma.challengeshop.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);

    boolean verifyJwtToken(String token);

    JwtClaims getClaimsByToken(String token);
}
