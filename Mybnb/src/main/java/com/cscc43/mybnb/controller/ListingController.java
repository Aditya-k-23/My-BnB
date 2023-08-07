package com.cscc43.mybnb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListingController {

  @GetMapping("/hi")
  public String root() {
    return "Welcome to the root endpoint!";
  }
}
