package com.mybank.controller;

import com.mybank.entity.AccountDetails;
import com.mybank.entity.Ewallet;
import com.mybank.service.BankMoneyTransferService;
import com.mybank.service.WalletMoneyTransferService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

import java.util.UUID;

@Controller("/account")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AccountsController {

    @Inject
    BankMoneyTransferService bankMoneyTransferService;

    @Inject
    WalletMoneyTransferService walletMoneyTransferService;


    @Get("/")
    public String hello(){
        return "hello you are in /accounts controller!";
    }

    @Post("/createAccount")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<AccountDetails> createAccount(@Body AccountDetails ac){
        return bankMoneyTransferService.createAccount(ac);
    }


    @Get("/transferMoney/{accountNumberOfReceiver}/{accountNumberOfSender}/{moneyToTransfer}")
    public HttpResponse transferMoney(@PathVariable  UUID accountNumberOfReceiver, @PathVariable  UUID accountNumberOfSender, @PathVariable  double moneyToTransfer){
      return  bankMoneyTransferService.transferMoneyUsingAccountNumber(accountNumberOfReceiver,accountNumberOfSender,moneyToTransfer);
    }

    @Get("/balance/{accountNumber}")
    HttpResponse getBankBalance(@PathVariable  UUID accountNumber){
        return bankMoneyTransferService.getBankBalance(accountNumber);
    }

    @Get("/myprofile/{accountNumber}")
    HttpResponse getMyProfile(@PathVariable  UUID accountNumber){
        return bankMoneyTransferService.getFullProfile(accountNumber);
    }

    @Get("/addMoneyToWallet/{ammountToAdd}/{phoneNumber}")
    HttpResponse addMoneyToWallet(@PathVariable double ammountToAdd, @PathVariable String phoneNumber){
        return walletMoneyTransferService.addMoneyToWallet(ammountToAdd,phoneNumber);

    }

    @Get("/transferMoneyUsingWalletAndPhoneNumber/{phoneNumberOfSender}/{phoneNumberOfReceiver}/{amountToTransfer}")
    public HttpResponse transferMoneyUsingWalletAndPhoneNumber(@PathVariable String phoneNumberOfSender,@PathVariable String phoneNumberOfReceiver,@PathVariable double amountToTransfer){
      return walletMoneyTransferService.transferMoneyUsingWalletAndPhoneNumber(phoneNumberOfSender,phoneNumberOfReceiver,amountToTransfer);
    }

    @Get("/getWalletBalance/{phoneNumber}")
    public HttpResponse getWalletBalance(@PathVariable String phoneNumber){
        return walletMoneyTransferService.getWalletBalance(phoneNumber);
    }

    @Post("/registerForEwallet")
    public HttpResponse registerForEwallet(@Body Ewallet e){
        return walletMoneyTransferService.registerForEwallet(e);
    }

}
