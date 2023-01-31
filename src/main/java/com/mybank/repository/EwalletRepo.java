package com.mybank.repository;

import com.mybank.entity.Ewallet;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface EwalletRepo extends CrudRepository<Ewallet,String> {

    Ewallet save(Ewallet e);

    Ewallet findByPhoneNumber(String phoneNumber);
}
