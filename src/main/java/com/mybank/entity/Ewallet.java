package com.mybank.entity;

import javax.persistence.*;

@Entity
@Table(name = "walletDetails")
public class Ewallet {
    public Ewallet(){

    }
    @Id
    private String phoneNumber;
    private double walletBalance;

    @OneToOne(targetEntity = UserEntity.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "id")
    private UserEntity userEntity;
    @OneToOne(targetEntity = AccountDetails.class)
    @JoinColumn(name = "accountNumber",referencedColumnName = "accountNumber")
    private AccountDetails accountDetails;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public Ewallet(String phoneNumber, double walletBalance, UserEntity userEntity, AccountDetails accountDetails) {
        this.phoneNumber = phoneNumber;
        this.walletBalance = walletBalance;
        this.userEntity = userEntity;
        this.accountDetails = accountDetails;
    }

    public Ewallet(String phoneNumber, double walletBalance) {
        this.phoneNumber = phoneNumber;
        this.walletBalance = walletBalance;


    }



}
