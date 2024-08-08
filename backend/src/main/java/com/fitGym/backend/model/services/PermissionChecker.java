package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.User;

public interface PermissionChecker {
    User checkUser(Long id);
}
