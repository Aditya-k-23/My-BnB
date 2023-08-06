package com.cscc43.mybnb.repository.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Listing;
import com.cscc43.mybnb.models.Period;

@Repository
public interface PeriodRepositoryInterface {
  public List<Period> getAllPeriods();

  public List<Period> getPeriods(int listing_id);

  public List<Period> getPeriods(LocalDate start_date, int listing_id);

  public List<Period> getPeriods(LocalDate start_date, LocalDate end_date);

  public List<Period> getPeriods(int listing_id, LocalDate end_date);

  public List<Listing> getAvailableListings();

  public String addPeriod(Period period);

  public void deletePeriod(int id, int listing_id);

}
