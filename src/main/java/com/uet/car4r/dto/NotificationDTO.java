package com.uet.car4r.dto;

import com.uet.car4r.constant.TypeMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ResponseDTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
  private String endpoint;
  private TypeMessage message;
  private String messageDetail;
}
