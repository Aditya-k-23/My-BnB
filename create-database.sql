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

-- Procedures
drop procedure if exists add_period;

DELIMITER //
create procedure add_period (
  in in_listing_id int,
  in in_start_date date,
  in in_end_date date,
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
      listing_id = in_listing_id
      and start_date <= in_end_date
      and end_date >= in_start_date
  ) then
  select
    'Period overlapping';

  leave sp;

  elseif exists (
    select
      *
    from
      Bookings
    where
      listing_id = in_listing_id
      and start_date <= in_end_date
      and end_date >= in_start_date
      and status != 'Cancelled'
  ) then
  select
    'Booking exists and conflicts with period';

  leave sp;

  end if;

  insert into
    Period (listing_id, start_date, end_date, price) value (
      in_listing_id,
      in_start_date,
      in_end_date,
      in_price
    );

  commit;

  select
    'Period added';

end //
DELIMITER ;

drop procedure if exists add_booking;

DELIMITER //
create procedure add_booking (
  in in_listing_id int,
  in in_start_date date,
  in in_end_date date,
  in in_renter_id int,
  in in_price float
)
sp:
  BEGIN declare existing_period_id int;

  declare existing_price float;

  declare existing_start_date date;

  declare existing_end_date date;

  declare existing_host_id int;

  start transaction;

  if not exists (
    select
      *
    from
      Period
    where
      listing_id = in_listing_id
      and start_date <= in_end_date
      and end_date >= in_start_date
  ) then
  select
    (
      concat ('No Availabilities for Listing: ', in_listing_id)
    );

  leave sp;

  end if;

  select
    Period.id,
    price,
    start_date,
    end_date,
    host_id into existing_period_id,
    existing_price,
    existing_start_date,
    existing_end_date,
    existing_host_id
  from
    Period
    inner join Listing on Period.listing_id = Listing.id
  where
    listing_id = in_listing_id
    and start_date <= in_start_date
    and in_end_date <= end_date;

  if in_start_date = existing_start_date and in_end_date = existing_end_date
  then
  delete from Period
  where
    id = existing_period_id;

  elseif in_start_date = existing_start_date
  and in_end_date < existing_end_date then
  update Period
  set
    start_date = DATE_ADD(in_end_date, interval 1 day)
  where
    id = existing_period_id;

  elseif in_start_date > existing_start_date
  and in_end_date = existing_end_date then
  update Period
  set
    end_date = DATE_SUB(in_start_date, interval 1 day)
  where
    id = existing_period_id;

  else
  update Period
  set
    start_date = DATE_ADD(in_end_date, interval 1 day)
  where
    id = existing_period_id;

  insert into
    Period (listing_id, start_date, end_date, price) value (
      in_listing_id,
      existing_start_date,
      DATE_SUB(in_start_date, interval 1 day),
      existing_price
    );

  end if;

  insert into
    Bookings (
      status,
      listing_id,
      start_date,
      end_date,
      renter_id,
      price
    ) value (
      'Booked',
      in_listing_id,
      in_start_date,
      in_end_date,
      in_renter_id,
      existing_price
    );

  commit;

  select
    (
      concat ('Booking added for Listing: ', in_listing_id)
    );

end //
DELIMITER ;

drop procedure if exists cancel_booking;

DELIMITER //
create procedure cancel_booking (in in_booking_id int)
sp:
  BEGIN

  declare existing_listing_id int;

  declare existing_price float;

  declare existing_start_date date;

  declare existing_end_date date;

  declare period_preceeding_start_date date;

  declare period_preceeding_id int;

  declare period_succeeding_id int;

  declare period_succeeding_end_date date;

  start transaction;

  if not exists (
    select
      *
    from
      Bookings
    where
      id = in_booking_id
  ) then
  select
    (
      concat ('Booking does not exist: ', in_booking_id)
    );

  select
    listing_id,
    start_date,
    end_date,
    price into existing_listing_id,
    existing_start_date,
    existing_end_date,
    existing_price
  from
    Bookings
  where
    id = in_booking_id;

  if exists (
    select
      *
    from
      Period
    where
      listing_id = existing_listing_id
      and end_date = DATE_SUB(existing_start_date, interval 1 day)
      and exists (
        select
          *
        from
          Period
        where
          listing_id = existing_listing_id
          and start_date = DATE_ADD(existing_end_date, interval 1 day)
      )
  ) then
  select
    id,
    start_date into period_preceeding_id,
    period_preceeding_start_date
  from
    Period
  where
    listing_id = existing_listing_id
    and end_date = DATE_SUB(existing_start_date, interval 1 day);

  select
    id,
    end_date into period_succeeding_id,
    period_succeeding_end_date
  from
    Period
  where
    listing_id = existing_listing_id
    and start_date = DATE_ADD(existing_end_date, interval 1 day);

  delete from Period
  where
    id = period_succeeding_id;

  update Period
  set
    end_date = period_succeeding_end_date
  where
    id = period_preceeding_id;

  elseif exists (
    select
      *
    from
      Period
    where
      listing_id = existing_listing_id
      and end_date = DATE_SUB(existing_start_date, interval 1 day)
  ) then
  select
    id into period_preceeding_id
  from
    Period
  where
    listing_id = existing_listing_id
    and end_date = DATE_SUB(existing_start_date, interval 1 day);

  update Period
  set
    end_date = existing_end_date
  where
    id = period_preceeding_id;

  elseif exists (
    select
      *
    from
      Period
    where
      listing_id = existing_listing_id
      and start_date = DATE_ADD(existing_end_date, interval 1 day)
  ) then
  select
    id into period_succeeding_id
  from
    Period
  where
    listing_id = existing_listing_id
    and start_date = DATE_ADD(existing_end_date, interval 1 day);

  update Period
  set
    start_date = existing_start_date
  where
    id = period_succeeding_id;

  else
  insert into
    Period (listing_id, start_date, end_date, price) value (
      existing_listing_id,
      existing_start_date,
      existing_end_date,
      existing_price
    );

  end if;

  update Bookings
  set
    status = 'Cancelled'
  where
    id = in_booking_id;

  commit;

  select
    'Booking cancelled';

  end if;

end //
DELIMITER ;

drop procedure if exists add_UserReview;
DELIMITER //
create procedure add_UserReview (
  in in_booking_id int,
  in in_reviewer_id int,
  in in_reviewee_id int,
  in in_rating int,
  in in_comment varchar(500)
)
sp:

begin

declare existing_reviewed_id int;

start transaction;

if not exists (
  select
    *
  from
    Bookings
  where
    id = in_booking_id
    and (host_id = in_reviewer_id or renter_id = in_reviewer_id)
    and status != 'Cancelled'
) then
select
  (
    concat ('Booking does not exist: ', in_booking_id)
  );
leave sp;
end if;

if exists (
  select
    *
  from
    UserReview
  where
    reviewer_id = in_reviewer_id
    and booking_id = in_booking_id
) then
update UserReview
set rating = in_rating,
  comment = in_comment
where
  reviewer_id = in_reviewer_id
  and booking_id = in_booking_id;
select
  (
    concat ('Review updated for Booking: ', in_booking_id)
  );
commit;
leave sp;
end if;

if exists (
  select
    *
  from
    Bookings
  where
    id = in_booking_id
    and host_id = in_reviewer_id
) then
select
  renter_id into existing_reviewed_id
from
  Bookings
where
  id = in_booking_id
  and host_id = in_reviewer_id;

else
select
  host_id into existing_reviewed_id
from
  Bookings
where
  id = in_booking_id
  and renter_id = in_reviewer_id;

end if;

insert into
  UserReview (reviewer_id, reviewed_id, booking_id, rating, comment) value (
    in_reviewer_id,
    existing_reviewed_id,
    in_booking_id,
    in_rating,
    in_comment
  );

commit;

select
  (
    concat ('Review added for Booking: ', in_booking_id)
  );

end //
DELIMITER ;

drop procedure if exists ListingReview;
DELIMITER //
create procedure add_ListingReview (
  in in_booking_id int,
  in in_renter_id int,
  in in_listing_id int,
  in in_rating int,
  in in_comment varchar(500)
)
sp:

begin

declare existing_reviewed_id int;

start transaction;

if not exists (
  select
    *
  from
    Bookings
  where
    id = in_booking_id
    and (renter_id = in_renter_id)
    and status != 'Cancelled'
) then
select
  (
    concat ('Booking does not exist: ', in_booking_id)
  );
leave sp;
end if;

if exists (
  select
    *
  from
    ListingReview
  where
    renter_id = in_renter_id
    and booking_id = in_booking_id
) then
update ListingReview
set rating = in_rating,
  comment = in_comment
where
  renter_id = in_renter_id
  and booking_id = in_booking_id;
select
  (
    concat ('Review updated for Booking: ', in_booking_id)
  );
commit;
leave sp;
end if;

insert into
  ListingReview (renter_id, listing_id, booking_id, rating, comment) value (
    in_renter_id,
    in_listing_id,
    in_booking_id,
    in_rating,
    in_comment
  );

commit;

select
  (
    concat ('Review added for Booking: ', in_booking_id)
  );

end //
DELIMITER ;