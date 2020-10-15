package com.wangluoguimi.social.authService.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacebookLoginRequest {

  @NotBlank
  private String accessToken;
}
