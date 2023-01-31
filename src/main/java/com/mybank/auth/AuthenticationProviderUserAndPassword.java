package com.mybank.auth;

import com.mybank.entity.UserEntity;
import com.mybank.repository.UserRepo;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Singleton
public class AuthenticationProviderUserAndPassword implements AuthenticationProvider {

    private final UserRepo userRepo;

    public AuthenticationProviderUserAndPassword(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        String identity = (String) authenticationRequest.getIdentity();
        return Mono.<AuthenticationResponse>create(emitter -> {

          Optional<UserEntity> user = userRepo.findByEmailId(identity);
          if(user.isPresent()){
              final String password = (String) authenticationRequest.getSecret();
              if(user.get().getPassword().equals(password)){
                  emitter.success(AuthenticationResponse.success(identity));
                  return;
              }
              else{
                  emitter.error(new AuthenticationException(new AuthenticationFailed("invalid usename or password")));
                  return;
              }
          }
          else {
              emitter.error(new AuthenticationException(new AuthenticationFailed("invalid usename or password")));
          }

        });
    }
}
