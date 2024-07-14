package com.enigma.challengeshop.service;

import com.enigma.challengeshop.constant.UserRole;
import com.enigma.challengeshop.entity.Role;

public interface RoleService {
    Role getOrSave(UserRole role);

}
