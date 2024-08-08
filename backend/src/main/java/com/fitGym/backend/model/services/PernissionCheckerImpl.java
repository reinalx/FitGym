package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.User;
import com.fitGym.backend.model.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class PernissionCheckerImpl implements PermissionChecker {


    @Override
    public User checkUser(Long id) {
        return null;
    }
}
