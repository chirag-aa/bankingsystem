package com.mybank.repository;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

@Client("/")
public interface CustomeRestClient {

    @Post("/login")
    BearerAccessRefreshToken login(@Body UsernamePasswordCredentials credentials);

}
