package com.cscc43.mybnb.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cscc43.mybnb.models.ListingCountReport;
import com.cscc43.mybnb.models.ListingReview;
import com.cscc43.mybnb.models.combined.CountryCityHostIDListing;
import com.cscc43.mybnb.models.combined.CountryCityListing;
import com.cscc43.mybnb.models.combined.CountryCityPostalListing;
import com.cscc43.mybnb.models.combined.CountryHostIDListing;
import com.cscc43.mybnb.models.combined.CountryListing;
import com.cscc43.mybnb.models.combined.RenterIDBookingCount;
import com.cscc43.mybnb.models.combined.RenterIDCityBookingCount;
import com.cscc43.mybnb.models.combined.YearUserIDBookingCount;
import com.cscc43.mybnb.repository.implementation.BookingRepository;
import com.cscc43.mybnb.repository.implementation.HostRepository;
import com.cscc43.mybnb.repository.implementation.ListingRepository;
import com.cscc43.mybnb.repository.implementation.ListingReviewRepository;
import com.cscc43.mybnb.repository.implementation.RenterRepository;
import com.cscc43.mybnb.services.NounPhrases;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/reports")
public class ReportsController {

  @Autowired
  BookingRepository bookingRepository;

  @Autowired
  ListingRepository listingRepository;

  @Autowired
  HostRepository hostRepository;

  @Autowired
  RenterRepository renterRepository;

  @Autowired
  ListingReviewRepository listingReviewRepository;

  @GetMapping(value = "/countBookingsWithinRange")
  public Integer getCountBookingsWithinRange(
      @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
      @RequestParam(value = "city", required = false) String city,
      @RequestParam(value = "postalCode", required = false) String postalCode) {
    return bookingRepository.getCountBookings(startDate, endDate, city, postalCode);
  }

  @GetMapping(value = "/countListingsByCountry")
  public List<CountryListing> getCountListingsByCountry() {
    return listingRepository.getCountryListingCount();
  }

  @GetMapping(value = "/countListingsByCountryCity")
  public List<CountryCityListing> getCountListingsByCountryCity() {
    return listingRepository.getCountryCityListingCount();
  }

  @GetMapping(value = "/countListingsByCountryCityPostalCode")
  public List<CountryCityPostalListing> getCountListingsByCountryCityPostalCode() {
    return listingRepository.getCountryCityPostalListingCount();
  }

  @GetMapping(value = "/rankHostsByNumberOfListingsCountry")
  public List<CountryHostIDListing> getRankHostsByNumberOfListingsCountry() {
    return hostRepository.getHostsByListingsPerCountry();
  }

  @GetMapping(value = "/rankHostsByNumberOfListingsCountryCity")
  public List<CountryCityHostIDListing> getRankHostsByNumberOfListingsCountryCity() {
    return hostRepository.getHostsByListingsPerCountryCity();
  }

  @GetMapping(value = "/rankHostsByCancellationsPerYear")
  public List<YearUserIDBookingCount> getHostsByCancellationsPerYear() {
    return hostRepository.getHostsByCancellationsPerYear();
  }

  @GetMapping(value = "/rankRentersByCancellationsPerYear")
  public List<YearUserIDBookingCount> getRentersByCancellationsPerYear() {
    return renterRepository.getRenterRankedByCancellationsInYear();
  }

  @GetMapping(value = "/getPotentialCommercialHosts")
  public List<ListingCountReport> getPotentialCommercialHosts() {
    return hostRepository.getFlaggedHosts();
  }

  @GetMapping(value = "/rankByBookingsInRange")
  public List<RenterIDBookingCount> getRankByBookingsInRange(
      @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    return renterRepository.getRenterRankedByBoookingsInPeriod(startDate, endDate);
  }

  @GetMapping(value = "/rankByBookingsInRangeByCity")
  public List<RenterIDCityBookingCount> getRankByBookingsInRangeByCity(
      @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
      @RequestParam("minBookingCount") int minBookingCount) {
    return renterRepository.getRenterRankedByBoookingsInRangePerCity(startDate, endDate, minBookingCount);
  }

  @GetMapping(value = "/userNounPhrases")
  public Map<Integer, Map<String, Integer>> getNounPhrasesCountByListing() {
    List<ListingReview> listingReviews = listingReviewRepository.getAllListingReviews();
    return NounPhrases.getPopularNounPhrases(listingReviews);
  }

}
