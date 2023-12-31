package com.cscc43.mybnb.repository.interfaces;

import java.util.List;
import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Renter;
import com.cscc43.mybnb.models.combined.RenterIDBookingCount;
import com.cscc43.mybnb.models.combined.RenterIDCityBookingCount;
import com.cscc43.mybnb.models.combined.YearUserIDBookingCount;

@Repository
public interface RenterRepositoryInterface {
  public List<Renter> getAllRenters();

  public Renter getRenter(int id);

  public void addRenter(Renter renter);

  public void deleteRenter(int id);

  public List<RenterIDBookingCount> getRenterRankedByBoookingsInPeriod(LocalDate startDate, LocalDate endDate);

  public List<RenterIDCityBookingCount> getRenterRankedByBoookingsInRangePerCity(LocalDate startDate, LocalDate endDate,
      int minBookingCount);

  public List<YearUserIDBookingCount> getRenterRankedByCancellationsInYear();
}
