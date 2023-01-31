package com.mybank.repository;


import com.mybank.entity.AccountDetails;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountsRepo extends CrudRepository<AccountDetails, UUID> {

    @Override
    List<AccountDetails> findAll();
    List<AccountDetails>saveAll(List<AccountDetails> entities);

    AccountDetails save(AccountDetails entity);

    Optional<AccountDetails> findByAccountNumber(UUID id);

}
