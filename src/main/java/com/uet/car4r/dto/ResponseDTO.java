package com.uet.car4r.dto;

import com.uet.car4r.constant.TypeMessage;
import lombok.Builder;
import lombok.Getter;

/**
 * ResponseDTO
 */
@Getter
@Builder
public class ResponseDTO {
  private String endpoint;
  private TypeMessage message;
  private String messageDetail;

}
