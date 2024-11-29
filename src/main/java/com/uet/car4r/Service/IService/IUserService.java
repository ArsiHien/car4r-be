package com.uet.car4r.Service.IService;

import com.uet.car4r.DTO.ResponseDTO;

public interface IUserService {
  public ResponseDTO login();
  public ResponseDTO register();
}
