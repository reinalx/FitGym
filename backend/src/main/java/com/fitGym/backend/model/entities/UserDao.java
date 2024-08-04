package com.fitGym.backend.model.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

}
