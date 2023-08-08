package com.cscc43.mybnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.controller.requestbodies.AddUserReviewBody;
import com.cscc43.mybnb.repository.implementation.UserReviewRepository;

@RestController
@RequestMapping("/userReview")
public class UserReviewController {
  @Autowired
  UserReviewRepository userReviewRepository;

  @PostMapping(value = { "/add", "/modify" })
  public void addUserReview(@RequestBody AddUserReviewBody userReview) {
    userReviewRepository.addUserReview(userReview);
  }

  @DeleteMapping(value = "/delete")
  public void deleteUserReview(@RequestParam("userReviewId") Integer userReviewId) {
    userReviewRepository.deleteUserReview(userReviewId);
  }
}
