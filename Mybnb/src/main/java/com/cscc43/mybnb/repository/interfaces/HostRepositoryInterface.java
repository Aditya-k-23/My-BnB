package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.Host;
import com.cscc43.mybnb.models.ListingCountReport;
import com.cscc43.mybnb.models.combined.CountryHostIDListing;
import com.cscc43.mybnb.models.combined.YearUserIDBookingCount;
import com.cscc43.mybnb.models.combined.CountryCityHostIDListing;

@Repository
public interface HostRepositoryInterface {
  public List<Host> getAllHosts();

  public void addHost(Host host);

  public Host getHost(int id);

  public void deleteHost(int id);

  public List<CountryHostIDListing> getHostsByListingsPerCountry();

  public List<CountryCityHostIDListing> getHostsByListingsPerCountryCity();

  public List<YearUserIDBookingCount> getHostsByCancellationsPerYear();

  public List<ListingCountReport> getFlaggedHosts();
}
