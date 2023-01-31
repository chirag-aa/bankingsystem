package com.mybank.service;

import com.mybank.entity.AccountDetails;
import com.mybank.repository.AccountsRepo;
import com.mybank.repository.EwalletRepo;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class BankMoneyTransferService {

    @Inject
    EwalletRepo ewalletRepo;

   private final AccountsRepo accountsRepo;

    public BankMoneyTransferService(AccountsRepo accountsRepo) {
        this.accountsRepo = accountsRepo;
    }

    public HttpResponse getBankBalance(UUID accountNumber){
       Optional<AccountDetails> ad = accountsRepo.findByAccountNumber(accountNumber);
       return HttpResponse.ok(ad.get().getAccountBalance());
    }

    public HttpResponse<AccountDetails> getFullProfile(UUID accountNumber){
        Optional<AccountDetails> ad = accountsRepo.findByAccountNumber(accountNumber);
        return HttpResponse.ok(ad.get());
    }

//    public double getBankBalance(String phoneNumber){
//        customerRepo.fi
//        return ad.getAccountBalance();
//    }

//    public void transferMoneyUsingPhoneNumber(String phoneNumber,double moneyToTransfer){
//
//    }

    public HttpResponse transferMoneyUsingAccountNumber(UUID accountNumberOfReceiver, UUID accountNumberOfSender, double moneyToTransfer){
        Optional<AccountDetails> ads = accountsRepo.findByAccountNumber(accountNumberOfSender);
        Optional<AccountDetails> adr = accountsRepo.findByAccountNumber(accountNumberOfReceiver);
       if(ads.isPresent() && adr.isPresent()){
           if(ads.get().getAccountBalance()>=moneyToTransfer){
               adr.get().setAccountBalance(adr.get().getAccountBalance() + moneyToTransfer);
               accountsRepo.update(adr.get());
               ads.get().setAccountBalance(ads.get().getAccountBalance() - moneyToTransfer);
               accountsRepo.update(ads.get());
           }
           else{
          //     System.out.println(ads.getAccountHolderName()+"Insufficient balance !");
               return HttpResponse.badRequest("Insufficient balance ");
           }

       }
       else{
           System.out.println("Data Not Found please enter valid credentials");
           return HttpResponse.badRequest("No Record Found in database");
       }
       return HttpResponse.ok("Transfer Successful " +" you current balance is "+ads.get().getAccountBalance());
    }

//    public void withdrawMoney(double moneyToWithdraw,String phoneNumber){
//
//    }

    public HttpResponse createAccount(AccountDetails ac) {
        if(ac.getUserEntity().getEmailId()=="" || ac.getUserEntity().getPhoneNumber()==""){
            return HttpResponse.badRequest("Email or PhoneNumber cannot be empty");
        }
        else{
            accountsRepo.save(ac);
            return HttpResponse.ok("Account created with username "+ac.getUserEntity().getEmailId());
        }
    }

}
