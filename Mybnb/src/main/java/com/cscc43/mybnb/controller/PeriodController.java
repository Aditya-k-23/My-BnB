package com.cscc43.mybnb.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.controller.requestbodies.DeletePeriodBody;
import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.Period;
import com.cscc43.mybnb.repository.implementation.PeriodRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/period")
public class PeriodController {

  @Autowired
  PeriodRepository periodRepository;

  @GetMapping("/all")
  public List<Period> getAllPeriods() {
    return periodRepository.getAllPeriods();
  }

  @GetMapping("/byListingId")
  public List<Period> getPeriodById(@RequestParam("listingId") int listing_id) {
    return periodRepository.getPeriods(listing_id);
  }

  @GetMapping("/getPeriods")
  public List<Period> getAllPeriods(@RequestParam("listingId") int listing_id,
      @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
      @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date) {
    if (start_date == null && end_date == null)
      return periodRepository.getPeriods(listing_id);
    else if (start_date == null)
      return periodRepository.getPeriods(listing_id, end_date);
    else
      return periodRepository.getPeriods(listing_id, start_date, end_date);
  }

  @GetMapping("/getPeriodsInRange")
  public List<Period> getPeriodsInRange(
      @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
      @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date) {
    return periodRepository.getPeriods(start_date, end_date);
  }

  @GetMapping("/getAllAvailableListings")
  public List<Listing> getAllAvailableListings() {
    return periodRepository.getAvailableListings();
  }

  @PostMapping("/addPeriod")
  public void addPeriod(@RequestBody Period period) {
    periodRepository.addPeriod(period);
  }

  @PostMapping(path = "/deletePeriod", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deletePeriod(@RequestBody DeletePeriodBody requestBody) {
    periodRepository.deletePeriod(requestBody.getPeriodId(), requestBody.getListingID());
  }

}
