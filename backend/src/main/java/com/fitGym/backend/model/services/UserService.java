package com.fitGym.backend.model.services;

import com.fitGym.backend.model.entities.User;
import com.fitGym.backend.model.exceptions.DuplicateInstanceException;
import com.fitGym.backend.model.exceptions.IncorrectLoginException;
import com.fitGym.backend.model.exceptions.InstanceNotFoundException;
import com.fitGym.backend.model.exceptions.IncorrectPasswordException;



public interface UserService {

    void signUp(User user) throws DuplicateInstanceException;

    User login(String userName, String password) throws IncorrectLoginException;

    User loginFromId(Long id) throws InstanceNotFoundException;

    User updateProfile(Long id, String firstName, String lastName, String email) throws InstanceNotFoundException;

    void changePassword(Long id, String oldPassword, String newPassword)
            throws InstanceNotFoundException, IncorrectPasswordException;
}
