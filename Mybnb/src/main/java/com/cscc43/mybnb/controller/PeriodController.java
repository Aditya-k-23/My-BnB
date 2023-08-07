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
  public List<Period> getPeriodById(@RequestParam("listingId") int listingId) {
    return periodRepository.getPeriods(listingId);
  }

  @GetMapping("/getPeriods")
  public List<Period> getAllPeriods(@RequestParam("listingId") int listingId,
      @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    if (startDate == null && endDate == null)
      return periodRepository.getPeriods(listingId);
    else if (startDate == null)
      return periodRepository.getPeriods(listingId, endDate);
    else
      return periodRepository.getPeriods(listingId, startDate, endDate);
  }

  @GetMapping("/getPeriodsInRange")
  public List<Period> getPeriodsInRange(
      @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    return periodRepository.getPeriods(startDate, endDate);
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
