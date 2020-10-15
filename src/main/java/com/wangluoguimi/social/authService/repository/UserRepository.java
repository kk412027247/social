package com.wangluoguimi.social.authService.repository;

import com.wangluoguimi.social.authService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
  Optional <User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
