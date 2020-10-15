package com.wangluoguimi.social.authService.payload;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserSummary {
  private String id;
  private String username;
  private String name;
  private String profilePicture;
}
