package com.uet.car4r.DTO;

import com.uet.car4r.Constant.TypeMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
