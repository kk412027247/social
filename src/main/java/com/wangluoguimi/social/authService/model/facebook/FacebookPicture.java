package com.wangluoguimi.social.authService.model.facebook;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookPicture {
  private PictureData data;
}
