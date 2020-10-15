package com.wangluoguimi.social.authService.endpoint;

import com.wangluoguimi.social.authService.exception.BadRequestException;
import com.wangluoguimi.social.authService.exception.EmailAlreadyExistsException;
import com.wangluoguimi.social.authService.exception.UsernameAlreadyExistsException;
import com.wangluoguimi.social.authService.model.Profile;
import com.wangluoguimi.social.authService.model.Role;
import com.wangluoguimi.social.authService.model.User;
import com.wangluoguimi.social.authService.payload.*;
import com.wangluoguimi.social.authService.service.FacebookService;
import com.wangluoguimi.social.authService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class AuthEndpoint {

  @Autowired
  private UserService userService;

  @Autowired
  private FacebookService facebookService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
    String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @PostMapping("/facebook/signin")
  public ResponseEntity<?> facebookAuth(@Valid @RequestBody FacebookLoginRequest facebookLoginRequest){
    log.info("facebook login {}", facebookLoginRequest);
    String token = facebookService.loginUser(facebookLoginRequest.getAccessToken());
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload){
    log.info("creating user {}", payload.getUsername());

    User user = User.builder()
        .username(payload.getUsername())
        .email(payload.getEmail())
        .password(payload.getPassword())
        .userProfile(Profile.builder().displayName(payload.getName()).build())
        .build();

    try{
      userService.registerUser(user, Role.USER);
    }catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e){
      throw new BadRequestException(e.getMessage());
    }

    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
        .buildAndExpand(user.getUsername()).toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "User register successfully"));
  }



}
