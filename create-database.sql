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
    addressLine varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    postalCode varchar(10) not null,
    primary key (addressLine, city, country, postalCode)
  );

create table
  if not exists User (
    id int auto_increment primary key,
    sin varchar(12) not null,
    name varchar(100) not null,
    addressLine varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    postalCode varchar(10) not null,
    occupation varchar(50) not null,
    birthdate date not null,
    foreign key (addressLine, city, country, postalCode) references Address (addressLine, city, country, postalCode) on delete cascade
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
    addressLine varchar(50) not null,
    city varchar(50) not null,
    country varchar(50) not null,
    postalCode varchar(10) not null,
    avgPrice float default null,
    hostId int not null,
    constraint fk_listing_host foreign key (hostId) references Host (id) on delete cascade,
    constraint fk_listing_address foreign key (addressLine, city, country, postalCode) references Address (addressLine, city, country, postalCode) on delete cascade
  );

create table
  if not exists Period (
    id int auto_increment primary key,
    startDate date not null,
    endDate date not null,
    price float not null,
    listingId int not null,
    constraint fk_period_listing foreign key (listingId) references Listing (id) on delete cascade,
    constraint check (startDate <= endDate)
  );

create table
  if not exists Amenity (
    name varchar(50) charset utf8 primary key,
    type varchar(50) charset utf8 default "general" not null
  );

create table
  if not exists ListingAmenity (
    listingId int not null,
    amenityName varchar(50) charset utf8 not null,
    constraint pk_listing_amenity primary key (listingId, amenityName),
    constraint fk_listing_amenity_listing foreign key (listingId) references Listing (id) on delete cascade,
    constraint fk_listing_amenity_amenity foreign key (amenityName) references Amenity (name) on delete cascade
  );

create table
  if not exists Booking (
    id int auto_increment primary key,
    status varchar(50) charset utf8 default "pending" not null,
    startDate date not null,
    endDate date not null,
    listingId int not null,
    renterId int not null,
    price float not null,
    constraint fk_booking_listing foreign key (listingId) references Listing (id) on delete cascade,
    constraint fk_booking_renter foreign key (renterId) references Renter (id) on delete cascade,
    constraint check (startDate <= endDate)
  );

create table
  if not exists PaymentInfo (
    id int auto_increment primary key,
    nameOnCard varchar(100) charset utf8 not null,
    cardNumber varchar(16) not null,
    postalCode varchar(10) not null,
    expiryDate date not null,
    userId int not null,
    constraint fk_payment_info_user foreign key (userId) references User (id) on delete cascade
  );

create table
  if not exists UserReview (
    id int auto_increment primary key,
    rating int not null,
    comment varchar(500) charset utf8 not null,
    bookingId int not null,
    reviewerId int not null,
    reviewedId int not null,
    constraint fk_review_reviewer foreign key (reviewerId) references User (id) on delete cascade,
    constraint fk_review_reviewed foreign key (reviewedId) references User (id) on delete cascade,
    constraint fk_review_booking_user foreign key (bookingId) references Booking (id) on delete cascade,
    constraint single_review_per_booking unique (bookingId, reviewerId)
  );

create table
  if not exists ListingReview (
    id int auto_increment primary key,
    rating int not null,
    comment varchar(500) charset utf8 not null,
    bookingId int not null,
    renterId int not null,
    listingId int not null,
    constraint fk_review_listing foreign key (listingId) references Listing (id) on delete cascade,
    constraint fk_review_renter foreign key (renterId) references Renter (id) on delete cascade,
    constraint fk_review_booking_listing foreign key (bookingId) references Booking (id) on delete cascade,
    constraint single_review_per_booking unique (bookingId, renterId)
  );

create table
  if not exists AmenitySearch (
    name varchar(50) charset utf8 not null primary key,
    searchCount int not null,
    constraint fk_amenity_search_amenity foreign key (name) references Amenity (name) on delete cascade
  );

-- Operations
create trigger add_period after insert on Period for each row
update Listing
set
  avgPrice = (
    select
      AVG(price)
    from
      Period
    where
      listingId = new.listingId
  )
where
  id = new.listingId;

create trigger delete_period after delete on Period for each row
update Listing
set
  avgPrice = (
    select
      AVG(price)
    from
      Period
    where
      listingId = old.listingId
  )
where
  id = old.listingId;

create trigger update_period after
update on Period for each row
update Listing
set
  avgPrice = (
    select
      AVG(price)
    from
      Period
    where
      listingId = new.listingId
  )
where
  id = new.listingId;

-- Procedures
drop procedure if exists sp_add_period;

DELIMITER //
create procedure sp_add_period (
  in in_listingId int,
  in in_startDate date,
  in in_endDate date,
  in in_price float
)
sp:
  begin start transaction;

  if exists (
    select
      *
    from
      Period
    where
      listingId = in_listingId
      and startDate <= in_endDate
      and endDate >= in_startDate
  ) then
  select
    'Period overlapping';

  leave sp;

  elseif exists (
    select
      *
    from
      Booking
    where
      listingId = in_listingId
      and startDate <= in_endDate
      and endDate >= in_startDate
      and status != 'Cancelled'
  ) then
  select
    'Booking exists and conflicts with period';

  leave sp;

  end if;

  insert into
    Period (listingId, startDate, endDate, price) value (
      in_listingId,
      in_startDate,
      in_endDate,
      in_price
    );

  commit;

  select
    'Period added';

end //
DELIMITER ;

drop procedure if exists sp_add_booking;
DELIMITER //
create procedure sp_add_booking (
  in in_listingId int,
  in in_startDate date,
  in in_endDate date,
  in in_renterId int
)
sp:
  BEGIN

  declare existing_periodId int;

  declare existing_price float;

  declare existing_startDate date;

  declare existing_endDate date;

  declare existing_hostId int;

  start transaction;

  if not exists (
    select
      *
    from
      Period
    where
      listingId = in_listingId
      and startDate <= in_endDate
      and endDate >= in_startDate
  ) then
  select
    (
      concat ('No Availabilities for Listing: ', in_listingId)
    );

  leave sp;

  end if;

  select
    Period.id,
    price,
    startDate,
    endDate,
    hostId into existing_periodId,
    existing_price,
    existing_startDate,
    existing_endDate,
    existing_hostId
  from
    Period
    inner join Listing on Period.listingId = Listing.id
  where
    listingId = in_listingId
    and startDate <= in_startDate
    and in_endDate <= endDate;

  if in_startDate = existing_startDate and in_endDate = existing_endDate
  then
  delete from Period
  where
    id = existing_periodId;

  elseif in_startDate = existing_startDate
  and in_endDate < existing_endDate then
  update Period
  set
    startDate = DATE_ADD(in_endDate, interval 1 day)
  where
    id = existing_periodId;

  elseif in_startDate > existing_startDate
  and in_endDate = existing_endDate then
  update Period
  set
    endDate = DATE_SUB(in_startDate, interval 1 day)
  where
    id = existing_periodId;

  else
  update Period
  set
    startDate = DATE_ADD(in_endDate, interval 1 day)
  where
    id = existing_periodId;

  insert into
    Period (listingId, startDate, endDate, price) value (
      in_listingId,
      existing_startDate,
      DATE_SUB(in_startDate, interval 1 day),
      existing_price
    );

  end if;

  insert into
    Booking (
      status,
      listingId,
      startDate,
      endDate,
      renterId,
      price
    ) value (
      'Booked',
      in_listingId,
      in_startDate,
      in_endDate,
      in_renterId,
      existing_price
    );

  commit;

  select
    (
      concat ('Booking added for Listing: ', in_listingId)
    );

end //
DELIMITER ;

drop procedure if exists cancel_booking;

DELIMITER //
create procedure cancel_booking (in in_bookingId int)
sp:
  BEGIN

  declare existing_listingId int;

  declare existing_price float;

  declare existing_startDate date;

  declare existing_endDate date;

  declare period_preceeding_startDate date;

  declare period_preceeding_id int;

  declare period_succeeding_id int;

  declare period_succeeding_endDate date;

  start transaction;

  if not exists (
    select
      *
    from
      Booking
    where
      id = in_bookingId
  ) then
  select
    (
      concat ('Booking does not exist: ', in_bookingId)
    );

  select
    listingId,
    startDate,
    endDate,
    price into existing_listingId,
    existing_startDate,
    existing_endDate,
    existing_price
  from
    Booking
  where
    id = in_bookingId;

  if exists (
    select
      *
    from
      Period
    where
      listingId = existing_listingId
      and endDate = DATE_SUB(existing_startDate, interval 1 day)
      and exists (
        select
          *
        from
          Period
        where
          listingId = existing_listingId
          and startDate = DATE_ADD(existing_endDate, interval 1 day)
      )
  ) then
  select
    id,
    startDate into period_preceeding_id,
    period_preceeding_startDate
  from
    Period
  where
    listingId = existing_listingId
    and endDate = DATE_SUB(existing_startDate, interval 1 day);

  select
    id,
    endDate into period_succeeding_id,
    period_succeeding_endDate
  from
    Period
  where
    listingId = existing_listingId
    and startDate = DATE_ADD(existing_endDate, interval 1 day);

  delete from Period
  where
    id = period_succeeding_id;

  update Period
  set
    endDate = period_succeeding_endDate
  where
    id = period_preceeding_id;

  elseif exists (
    select
      *
    from
      Period
    where
      listingId = existing_listingId
      and endDate = DATE_SUB(existing_startDate, interval 1 day)
  ) then
  select
    id into period_preceeding_id
  from
    Period
  where
    listingId = existing_listingId
    and endDate = DATE_SUB(existing_startDate, interval 1 day);

  update Period
  set
    endDate = existing_endDate
  where
    id = period_preceeding_id;

  elseif exists (
    select
      *
    from
      Period
    where
      listingId = existing_listingId
      and startDate = DATE_ADD(existing_endDate, interval 1 day)
  ) then
  select
    id into period_succeeding_id
  from
    Period
  where
    listingId = existing_listingId
    and startDate = DATE_ADD(existing_endDate, interval 1 day);

  update Period
  set
    startDate = existing_startDate
  where
    id = period_succeeding_id;

  else
  insert into
    Period (listingId, startDate, endDate, price) value (
      existing_listingId,
      existing_startDate,
      existing_endDate,
      existing_price
    );

  end if;

  update Booking
  set
    status = 'Cancelled'
  where
    id = in_bookingId;

  commit;

  select
    'Booking cancelled';

  end if;

end //
DELIMITER ;

drop procedure if exists add_user_review;
DELIMITER //
create procedure add_user_review (
  in in_bookingId int,
  in in_reviewerId int,
  in in_reviewee_id int,
  in in_rating int,
  in in_comment varchar(500)
)
sp:

begin

declare existing_reviewedId int;

start transaction;

if not exists (
  select
    *
  from
    Booking inner join Listing on Booking.listingId=Listing.id
  where
    Booking.id = in_bookingId
    and (hostId = in_reviewerId or renterId = in_reviewerId)
    and status != 'Cancelled'
) then
select
  (
    concat ('Booking does not exist: ', in_bookingId)
  );
leave sp;
end if;

if exists (
  select
    *
  from
    UserReview
  where
    reviewerId = in_reviewerId
    and bookingId = in_bookingId
) then
update UserReview
set rating = in_rating,
  comment = in_comment
where
  reviewerId = in_reviewerId
  and bookingId = in_bookingId;
select
  (
    concat ('Review updated for Booking: ', in_bookingId)
  );
commit;
leave sp;
end if;

if exists (
  select
    *
  from
    Booking inner join Listing on Booking.listingId=Listing.id
  where
    Booking.id = in_bookingId
    and hostId = in_reviewerId
) then
select
  renterId into existing_reviewedId
from
  Booking inner join Listing on Booking.listingId=Listing.id
where
  Booking.id = in_bookingId
  and hostId = in_reviewerId;

else
select
  hostId into existing_reviewedId
from
  Booking inner join Listing on Booking.listingId=Listing.id
where
  Booking.id = in_bookingId
  and renterId = in_reviewerId;

end if;

insert into
  UserReview (reviewerId, reviewedId, bookingId, rating, comment) value (
    in_reviewerId,
    existing_reviewedId,
    in_bookingId,
    in_rating,
    in_comment
  );

commit;

select
  (
    concat ('Review added for Booking: ', in_bookingId)
  );

end //
DELIMITER ;

drop procedure if exists add_listing_review;
DELIMITER //
create procedure add_listing_review (
  in in_bookingId int,
  in in_renterId int,
  in in_listingId int,
  in in_rating int,
  in in_comment varchar(500)
)
sp:

begin

declare existing_reviewedId int;

start transaction;

if not exists (
  select
    *
  from
    Booking
  where
    id = in_bookingId
    and (renterId = in_renterId)
    and (listingId = in_listingId)
    and status != 'Cancelled'
) then
select
  (
    concat ('Invalid request for reviewing booking: ', in_bookingId)
  );
leave sp;
end if;

if exists (
  select
    *
  from
    ListingReview
  where
    renterId = in_renterId
    and bookingId = in_bookingId
) then
update ListingReview
set rating = in_rating,
  comment = in_comment
where
  renterId = in_renterId
  and bookingId = in_bookingId;
select
  (
    concat ('Review updated for Booking: ', in_bookingId)
  );
commit;
leave sp;
end if;

insert into
  ListingReview (renterId, listingId, bookingId, rating, comment) value (
    in_renterId,
    in_listingId,
    in_bookingId,
    in_rating,
    in_comment
  );

commit;

select
  (
    concat ('Review added for Booking: ', in_bookingId)
  );

end //
DELIMITER ;