package com.uet.car4r.controller;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
public class ViewController {
  @GetMapping(value = "/{path:[^\\.]*}")
  public String redirect() {
    return "forward:/index.html";
  }

  @GetMapping(value =  "/")
  public String getIndex() {
    return "forward:/index.html";
  }
}