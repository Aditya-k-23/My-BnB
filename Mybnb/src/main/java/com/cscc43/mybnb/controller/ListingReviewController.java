package com.cscc43.mybnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.controller.requestbodies.AddListingReviewBody;
import com.cscc43.mybnb.repository.implementation.ListingReviewRepository;

@RestController
@RequestMapping("/listingReview")
public class ListingReviewController {
  @Autowired
  ListingReviewRepository listingReviewRepository;

  @PostMapping(value = { "/add", "/modify" })
  public void addListingReview(@RequestBody AddListingReviewBody listingReview) {
    listingReviewRepository.addListingReview(listingReview);
  }

  @DeleteMapping(value = "/delete")
  public void deleteListingReview(@RequestParam("listingReviewId") Integer listingReviewId) {
    listingReviewRepository.deleteListingReview(listingReviewId);
  }
}