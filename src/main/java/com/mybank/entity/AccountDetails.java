package com.mybank.entity;

import javax.persistence.*;
import java.util.UUID;


@Entity(name = "AccountDetailsTable")
@Table(name = "AccountDetailsTable")
public class AccountDetails {
    public AccountDetails() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountNumber;
    private String accountType;
    private double accountBalance;

    @OneToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "CustomerId" ,referencedColumnName = "id")
    private UserEntity userEntity;


    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
