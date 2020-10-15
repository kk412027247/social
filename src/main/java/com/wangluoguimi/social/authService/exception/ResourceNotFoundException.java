package com.wangluoguimi.social.authService.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(String.format("Resource %s not found", message));
  }
}
