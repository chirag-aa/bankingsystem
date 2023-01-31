package com.mybank.service;


import com.mybank.entity.Ewallet;
import com.mybank.repository.EwalletRepo;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;

public class WalletMoneyTransferService {
    @Inject
    EwalletRepo ewalletrepo;


    public HttpResponse registerForEwallet(Ewallet ewallet){
        if(ewallet.getPhoneNumber().length()==10 && ewallet.getAccountDetails().getAccountNumber().toString().length()>10 && ewallet.getUserEntity()!=null){
            return HttpResponse.created(ewalletrepo.save(ewallet));
        }
        else{
            return HttpResponse.notFound("please provide valid values");
        }
    }

 public HttpResponse addMoneyToWallet(double ammountToAdd, String phoneNumber){

        Ewallet e =ewalletrepo.findByPhoneNumber(phoneNumber);
        if(ammountToAdd>10000){
            System.out.println("Maximum you can add is Rs 10000");
            return HttpResponse.badRequest("Maximum you can add is Rs 10000 Rs");

        }
       else if(e.getWalletBalance()>10000){
           System.out.println("Max limit of wallet Balance is 10000");
            return HttpResponse.badRequest("Max limit of wallet is Reached");
       }
       else{
           double totalWalletBalance = e.getWalletBalance() + ammountToAdd;
           e.setWalletBalance(totalWalletBalance);
           ewalletrepo.update(e);
       //    ewalletrepo.save(e);
        }
         return HttpResponse.ok("Money Added Successfully Wallet balance is"+ e.getWalletBalance());
    }

    public HttpResponse transferMoneyUsingWalletAndPhoneNumber(String phoneNumberOfSender,String phoneNumberOfReceiver,double amountToTransfer){
       Ewallet es = ewalletrepo.findByPhoneNumber(phoneNumberOfSender);
       Ewallet er = ewalletrepo.findByPhoneNumber(phoneNumberOfReceiver);
        if(amountToTransfer>10000){
            System.out.println("Maximum you can Transfer is Rs 10000");
            return HttpResponse.badRequest("Maximum you can Transfer is Rs 10000");
        }
        if(phoneNumberOfSender.length()<10 || phoneNumberOfReceiver.length()<10){
            System.out.println("Invalid Phone Number");
            return HttpResponse.badRequest("Invalid Phone Number");
        }
        if(es.getWalletBalance()<amountToTransfer){
            System.out.println("Insufficient Balance");
            return HttpResponse.badRequest("Insufficient Balance");
        }
        if(er.getWalletBalance()>10000){
            System.out.println("Cannot Transfer Money Max Limit reached");
            return HttpResponse.badRequest("Cannot Transfer Money Max Limit reached");
        }
        else{
            es.setWalletBalance(es.getWalletBalance() - amountToTransfer);
            er.setWalletBalance(er.getWalletBalance()+amountToTransfer);
            ewalletrepo.update(es);
            ewalletrepo.update(er);
            System.out.println("Transfered "+ amountToTransfer + "To "+ phoneNumberOfSender);
        }
        return HttpResponse.ok("Transfer Successful");
    }


    public HttpResponse getWalletBalance(String phoneNumber) {
     Ewallet e = ewalletrepo.findByPhoneNumber(phoneNumber);
     return HttpResponse.ok("Wallet balance is " +e.getWalletBalance());
    }
}
