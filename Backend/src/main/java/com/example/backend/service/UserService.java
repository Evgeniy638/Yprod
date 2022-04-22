package com.example.backend.service;

import com.example.backend.entities.GoogleUserInfo;
import com.example.backend.entities.User;
import com.example.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends OidcUserService {

    private final UserRepo userRepo;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        try {
            return processOidcUser(oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        Optional<User> userOptional = userRepo.findById(googleUserInfo.getId());
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setId(googleUserInfo.getId());
            user.setEmail(googleUserInfo.getEmail());
            user.setName(googleUserInfo.getName());
            user.setPicture(googleUserInfo.getPicture());
            userRepo.save(user);
        }
        return oidcUser;
    }
}
