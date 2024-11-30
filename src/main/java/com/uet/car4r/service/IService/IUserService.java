package com.uet.car4r.service.IService;

import com.uet.car4r.dto.ResponseDTO;

public interface IUserService {
  public ResponseDTO login();
  public ResponseDTO register();
}
