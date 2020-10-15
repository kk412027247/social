package com.wangluoguimi.social.authService.model.facebook;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookUser {
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private FacebookPicture picture;
}
