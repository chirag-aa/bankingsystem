package com.mybank.repository;

import com.mybank.entity.UserEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity ,Integer> {

    Optional<UserEntity> findByEmailId(String email);
}
