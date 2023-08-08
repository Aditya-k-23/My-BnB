package com.cscc43.mybnb.repository.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.Period;

@Repository
public interface PeriodRepositoryInterface {
  public List<Period> getAllPeriods();

  public List<Period> getPeriods(int listingId);

  public List<Period> getPeriods(int listingId, LocalDate startDate, LocalDate endDate);

  public List<Period> getPeriods(LocalDate startDate, LocalDate endDate);

  public List<Period> getPeriods(int listingId, LocalDate endDate);

  public List<Listing> getAvailableListings();

  public String addPeriod(Period period);

  public void deletePeriod(int id);

}
