package com.mybank;

import com.mybank.repository.CustomeRestClient;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class BankingsystemTest {

    @Inject
    @Client("/")
    CustomeRestClient client1;

    @Inject
    @Client("/account")
    HttpClient client;

    @Test
    void testDemoController(){
        BearerAccessRefreshToken brt = client1.login(new UsernamePasswordCredentials("user","password"));

//        BearerAccessRefreshToken brt = generateAccessToken();
//        brt.getAccessToken();
//        assertNotNull(brt.getAccessToken());
//        assertEquals("user",brt.getUsername());
//        System.out.println("Your Access Token Is HERE   " + brt.getAccessToken());
        assertEquals("user",brt.getUsername());
        var request =HttpRequest.GET("/").accept(MediaType.APPLICATION_JSON).bearerAuth(brt.getAccessToken());
        var response  = client.toBlocking().exchange(request, String.class);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatus());
        assertEquals("hello!",response.getBody().get().toString());


    }

    @Test
    void createAccountTest(){
        BearerAccessRefreshToken brt = generateAccessToken();
        brt.getAccessToken();
        assertNotNull(brt.getAccessToken());
        assertEquals("user",brt.getUsername());
        System.out.println("Your Access Token Is HERE   " + brt.getAccessToken());

//        public AccountDetails createAccount(AccountDetails ac) {
//            accountsRepo.save(ac);
//            return ac;
//        }

//        CustomerDetails cd = new CustomerDetails();
//        AccountDetails ad =new AccountDetails();
//         var request = HttpRequest.POST("/createAccount");
//        @Post("/createAccount")
//        @Secured(SecurityRule.IS_ANONYMOUS)
//        public AccountDetails createAccount(@Body AccountDetails ac){
//            System.out.println(ac);
//            return bankMoneyTransferService.createAccount(ac);
//        }


    }

    private BearerAccessRefreshToken generateAccessToken() {
        final UsernamePasswordCredentials usernamePasswordCredentials =new UsernamePasswordCredentials("user","password");
        var login = HttpRequest.POST("/login",usernamePasswordCredentials);
        var response1 = client.toBlocking().exchange(login, BearerAccessRefreshToken.class);
        return response1.body();
    }


}
