package com.cscc43.mybnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.Renter;
import com.cscc43.mybnb.models.User;
import com.cscc43.mybnb.repository.implementation.RenterRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
public class RenterController {
  @Autowired
  RenterRepository renterRepository;

  @GetMapping("/getAllRenters")
  public List<Renter> getAllRenters() {
    return renterRepository.getAllRenters();
  }

  @GetMapping("/getRenter")
  public @ResponseBody User getRenter(@RequestParam("id") int id) {
    return renterRepository.getRenter(id);
  }

  @PostMapping("/addRenter")
  public void addRenter(@RequestBody Renter renter) {
    renterRepository.addRenter(renter);
  }

  @PostMapping("/deleteRenter")
  public void deleteRenter(@RequestParam("id") int id) {
    renterRepository.deleteRenter(id);
  }
}
