-- Run this script to drop the database and tables
-- Type the following command in the terminal:
-- mysql -u root -p < drop-database.sql
-- Or the following in the MySQL command line:
-- source drop-database.sql
-- Tables
drop table if exists ListingReview;

drop table if exists UserReview;

drop table if exists PaymentInfo;

drop table if exists Booking;

drop table if exists ListingAmenity;

drop table if exists AmenitySearch;

drop table if exists Amenity;

drop table if exists Period;

drop table if exists Listing;

drop table if exists Renter;

drop table if exists Host;

drop table if exists User;

drop table if exists Address;

-- Triggers
drop trigger if exists add_period;

drop trigger if exists delete_period;

drop trigger if exists update_period;

-- Database
drop database if exists Mybnb;