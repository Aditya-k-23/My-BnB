package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.User;
import com.cscc43.mybnb.repository.implementation.AddressRepository;
import com.cscc43.mybnb.repository.implementation.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
public class UserController {
  @Autowired
  UserRepository userRepository;

  @Autowired
  AddressRepository addressRepository;

  @GetMapping("/getAllUsers")
  public List<User> getAllUsers() {
    return userRepository.getAllUsers();
  }

  @GetMapping("/getUser")
  public @ResponseBody User getUser(@RequestParam("id") int id) {
    return userRepository.getUser(id);
  }

  @PostMapping(path = "/addUser")
  public void addUser(@RequestBody User user) {
    addressRepository.addAddress(user.getAddressLine(), user.getCity(), user.getCountry(),
        user.getPostalCode());
    userRepository.addUser(user);
  }

  @PostMapping("/deleteUser")
  public void deleteUser(@RequestParam("id") int id) {
    userRepository.deleteUser(id);
  }

}
