-- Run this script to create the database and tables
-- Type the following command in the terminal:
-- mysql -u root -p < create-database.sql
-- Or the following in the MySQL command line:
-- source create-database.sql
-- Database
create database if not exists Mybnb;

use Mybnb;

-- Entities
create table
  if not exists Address (
    address_line varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    postal_code varchar(10) not null,
    primary key (address_line, city, country, postal_code)
  );

create table
  if not exists User (
    id int auto_increment primary key,
    sin varchar(12) not null,
    name varchar(100) not null,
    address_line varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    postal_code varchar(10) not null,
    occupation varchar(50) not null,
    birthdate date not null,
    foreign key (address_line, city, country, postal_code) references Address (address_line, city, country, postal_code) on delete cascade
  );

create table
  if not exists Host (
    id int auto_increment primary key,
    constraint fk_host_user foreign key (id) references User (id) on delete cascade
  );

create table
  if not exists Renter (
    id int auto_increment primary key,
    constraint fk_renter_user foreign key (id) references User (id) on delete cascade
  );

create table
  if not exists Listing (
    id int auto_increment primary key,
    type varchar(50) charset utf8 default "house" not null,
    latitude decimal(8, 6) not null,
    longitude decimal(9, 6) not null,
    address_line varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    postal_code varchar(10) not null,
    avgPricePerNight float default null,
    host_id int not null,
    constraint fk_listing_host foreign key (host_id) references Host (id) on delete cascade,
    constraint fk_listing_address foreign key (address_line, city, country, postal_code) references Address (address_line, city, country, postal_code) on delete cascade
  );

create table
  if not exists Period (
    id int auto_increment primary key,
    start_date date not null,
    end_date date not null,
    price float not null,
    listing_id int not null,
    constraint fk_period_listing foreign key (listing_id) references Listing (id) on delete cascade,
    constraint check (start_date <= end_date)
  );

create table
  if not exists Amenity (
    name varchar(50) charset utf8 primary key,
    type varchar(50) charset utf8 default "general" not null
  );

create table
  if not exists ListingAmenity (
    listing_id int not null,
    amenity_name varchar(50) charset utf8 not null,
    constraint pk_listing_amenity primary key (listing_id, amenity_name),
    constraint fk_listing_amenity_listing foreign key (listing_id) references Listing (id) on delete cascade,
    constraint fk_listing_amenity_amenity foreign key (amenity_name) references Amenity (name) on delete cascade
  );

create table
  if not exists Bookings (
    id int auto_increment primary key,
    status varchar(50) charset utf8 default "pending" not null,
    start_date date not null,
    end_date date not null,
    listing_id int not null,
    renter_id int not null,
    price float not null,
    constraint fk_bookings_listing foreign key (listing_id) references Listing (id) on delete cascade,
    constraint fk_bookings_renter foreign key (renter_id) references Renter (id) on delete cascade,
    constraint check (start_date <= end_date)
  );

create table
  if not exists PaymentInfo (
    id int auto_increment primary key,
    name_on_card varchar(100) charset utf8 not null,
    card_number varchar(16) not null,
    postal_code varchar(10) not null,
    expiry_date date not null,
    user_id int not null,
    constraint fk_payment_info_user foreign key (user_id) references User (id) on delete cascade
  );

create table
  if not exists UserReview (
    id int auto_increment primary key,
    rating int not null,
    comment varchar(500) charset utf8 not null,
    booking_id int not null,
    reviewer_id int not null,
    reviewed_id int not null,
    constraint fk_review_reviewer foreign key (reviewer_id) references User (id) on delete cascade,
    constraint fk_review_reviewed foreign key (reviewed_id) references User (id) on delete cascade,
    constraint fk_review_booking_user foreign key (booking_id) references Bookings (id) on delete cascade,
    constraint single_review_per_booking unique (booking_id, reviewer_id)
  );

create table
  if not exists ListingReview (
    id int auto_increment primary key,
    rating int not null,
    comment varchar(500) charset utf8 not null,
    booking_id int not null,
    renter_id int not null,
    listing_id int not null,
    constraint fk_review_listing foreign key (listing_id) references Listing (id) on delete cascade,
    constraint fk_review_renter foreign key (renter_id) references Renter (id) on delete cascade,
    constraint fk_review_booking_listing foreign key (booking_id) references Bookings (id) on delete cascade,
    constraint single_review_per_booking unique (booking_id, renter_id)
  );

-- Operations
create trigger add_period after insert on Period for each row
update Listing
set
  avgPricePerNight = (
    select
      AVG(price)
    from
      Period
    where
      listing_id = new.listing_id
  )
where
  id = new.listing_id;

create trigger delete_period after delete on Period for each row
update Listing
set
  avgPricePerNight = (
    select
      AVG(price)
    from
      Period
    where
      listing_id = old.listing_id
  )
where
  id = old.listing_id;

create trigger update_period after
update on Period for each row
update Listing
set
  avgPricePerNight = (
    select
      AVG(price)
    from
      Period
    where
      listing_id = new.listing_id
  )
where
  id = new.listing_id;