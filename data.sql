-- Run this script to add data into the database
-- Type the following command in the terminal:
-- mysql -u root -p < data.sql
-- Or the following in the MySQL command line:
-- source data.sql
-- Database
use Mybnb;

insert into
  Address (address_line, city, country, postal_code)
values
  ('579 Loomis St', 'Westfield', 'USA', '01085'),
  ('581 Loomis St', 'Westfield', 'USA', '01085'),
  ('73 Honey Pot Rd', 'Southwick', 'USA', '01077'),
  ('2224 Glover', 'Langley', 'Canada', 'V3A 4P6'),
  ('2511 Victoria', 'Toronto', 'Canada', 'M2J 3T7'),
  ('2845 Bank St', 'Ottawa', 'Canada', 'K1H 7Z1'),
  ('4146 Munsee St', 'Selkirk', 'Canada', 'N0A 1P0'),
  ('2706 Jasper', 'Edmonton', 'Canada', 'T5J 3N6'),
  ('386 Joanna Dr', 'Wauseon', 'USA', '43567'),
  ('527 Tycos Dr', 'Toronto', 'Canada', 'M5T 1T4'),
  ('747 rue Garneau', 'Quebec', 'Canada', 'G1V 3V5'),
  ('789 Vernon Ave', 'Glencoe', 'USA', '60022');

insert into
  User (
    id,
    name,
    birthdate,
    occupation,
    sin,
    address_line,
    city,
    country,
    postal_code
  )
values
  (
    1,
    'Subramanian Kishore',
    '1998-10-05',
    'Software Engineer',
    '437 888 9890',
    '2706 Jasper',
    'Edmonton',
    'Canada',
    'T5J 3N6'
  ),
  (
    2,
    'Viktoras Margita',
    '2000-01-01',
    'Web Developer',
    '223 678 9876',
    '789 Vernon Ave',
    'Glencoe',
    'USA',
    '60022'
  ),
  (
    3,
    'Fionn Christa',
    '2000-07-12',
    'Marketing Analyst',
    '786 556 4342',
    '386 Joanna Dr',
    'Wauseon',
    'USA',
    '43567'
  ),
  (
    4,
    'Bella Melpomene',
    '1995-09-13',
    'Lawyer',
    '898 776 5274',
    '527 Tycos Dr',
    'Toronto',
    'Canada',
    'M5T 1T4'
  ),
  (
    5,
    'Jaci Aislin',
    '1995-12-12',
    'Student',
    '890 567 2436',
    '747 rue Garneau',
    'Quebec',
    'Canada',
    'G1V 3V5'
  );

insert into
  Renter
values
  (2);

insert into
  Renter
values
  (3);

insert into
  Renter
values
  (5);

insert into
  Host
values
  (1);

insert into
  Host
values
  (4);

insert into
  Listing (
    id,
    type,
    latitude,
    longitude,
    address_line,
    city,
    country,
    postal_code,
    host_id
  )
values
  (
    1,
    'Room',
    '42.100237',
    '-72.810496',
    '579 Loomis St',
    'Westfield',
    'USA',
    '01085',
    1
  ),
  (
    2,
    'Condo',
    '42.099843',
    '-72.810848',
    '581 Loomis St',
    'Westfield',
    'USA',
    '01085',
    1
  ),
  (
    3,
    'Mansion',
    '42.098518',
    '-72.824477',
    '73 Honey Pot Rd',
    'Southwick',
    'USA',
    '01077',
    1
  ),
  (
    4,
    'Apartment',
    '49.119268',
    '-122.674477',
    '2224 Glover',
    'Langley',
    'Canada',
    'V3A 4P6',
    1
  ),
  (
    5,
    'Condo',
    '43.790751',
    '-79.334217',
    '2511 Victoria',
    'Toronto',
    'Canada',
    'M2J 3T7',
    4
  ),
  (
    6,
    'Cabin',
    '45.380758',
    '-75.668002',
    '2845 Bank St',
    'Ottawa',
    'Canada',
    'K1H 7Z1',
    4
  ),
  (
    7,
    'House',
    '42.830732',
    '-79.932883',
    '4146 Munsee St',
    'Selkirk',
    'Canada',
    'N0A 1P0',
    4
  );

insert into
  Amenity (type, name)
values
  ('Bathroom', 'Bathtub'),
  ('Bathroom', 'Bidet'),
  ('Bathroom', 'Body soap'),
  ('Bathroom', 'Cleaning products'),
  ('Bathroom', 'Conditioner'),
  ('Bathroom', 'Hair dryer'),
  ('Bathroom', 'Hot water'),
  ('Bathroom', 'Outdoor shower'),
  ('Bathroom', 'Shampoo'),
  ('Bathroom', 'Shower gel'),
  ('Bedroom and laundry', 'Essentials'),
  ('Bedroom and laundry', 'Bed linens'),
  ('Bedroom and laundry', 'Clothing storage'),
  ('Bedroom and laundry', 'Dryer'),
  ('Bedroom and laundry', 'Drying rack for clothing'),
  (
    'Bedroom and laundry',
    'Extra pillows and blankets'
  ),
  ('Bedroom and laundry', 'Hangers'),
  ('Bedroom and laundry', 'Iron'),
  ('Bedroom and laundry', 'Mosquito net'),
  ('Bedroom and laundry', 'Room-darkening shades'),
  ('Bedroom and laundry', 'Safe'),
  ('Bedroom and laundry', 'Washer'),
  ('Entertainment', 'Ethernet connection'),
  ('Entertainment', 'Game console'),
  ('Entertainment', 'Piano'),
  ('Entertainment', 'Ping pong table'),
  ('Entertainment', 'Pool table'),
  ('Entertainment', 'Record player'),
  ('Entertainment', 'Sound system'),
  ('Entertainment', 'TV'),
  ('Family', 'Baby bath'),
  ('Family', 'Baby monitor'),
  ('Family', 'BabyCooking basicsChanging table'),
  ('Family', 'Children’s books and toys'),
  ('Family', 'Children’s dinnerware'),
  ('Family', 'Crib'),
  ('Family', 'Fireplace guards'),
  ('Family', 'High chair'),
  ('Family', 'Outlet covers'),
  ('Family', 'Pack ’n play/Travel crib'),
  ('Family', 'Table corner guards'),
  ('Family', 'Window guards'),
  ('Family', 'Board games'),
  ('Heating and cooling', 'Air conditioning'),
  ('Heating and cooling', 'Ceiling fan'),
  ('Heating and cooling', 'Heating'),
  ('Heating and cooling', 'Indoor fireplace'),
  ('Heating and cooling', 'Portable fans'),
  ('Home safety', 'Carbon monoxide alarm'),
  ('Home safety', 'Fire extinguisher'),
  ('Home safety', 'First aid kit'),
  ('Home safety', 'Smoke alarm'),
  ('Internet and office', 'Dedicated workspace'),
  ('Internet and office', 'Pocket wifi'),
  ('Internet and office', 'Wifi'),
  ('Kitchen and dining', 'Baking sheet'),
  ('Kitchen and dining', 'Barbecue utensils'),
  ('Kitchen and dining', 'Bread maker'),
  ('Kitchen and dining', 'Coffee maker'),
  ('Kitchen and dining', 'Cooking basics'),
  ('Kitchen and dining', 'Dining table'),
  ('Kitchen and dining', 'Dishes and silverware'),
  ('Kitchen and dining', 'Dishwasher'),
  ('Kitchen and dining', 'Freezer'),
  ('Kitchen and dining', 'Hot water kettle'),
  ('Kitchen and dining', 'Kitchen'),
  ('Kitchen and dining', 'Microwave'),
  ('Kitchen and dining', 'Mini fridge'),
  ('Kitchen and dining', 'Oven'),
  ('Kitchen and dining', 'Refrigerator'),
  ('Kitchen and dining', 'Stove'),
  ('Kitchen and dining', 'Toaster'),
  ('Kitchen and dining', 'Rice cooker'),
  ('Kitchen and dining', 'Trash compactor'),
  ('Kitchen and dining', 'Wine glasses'),
  ('Location features', 'Beach access'),
  ('Location features', 'Lake access'),
  ('Location features', 'Laundromat nearby'),
  ('Location features', 'Private entrance'),
  ('Location features', 'Ski-in/Ski-out'),
  ('Location features', 'Waterfront'),
  ('Outdoor', 'Backyard'),
  ('Outdoor', 'BBQ grill'),
  ('Outdoor', 'Beach essentials'),
  ('Outdoor', 'Bikes'),
  ('Outdoor', 'Boat slip'),
  ('Outdoor', 'Fire pit'),
  ('Outdoor', 'Kayak'),
  ('Outdoor', 'Outdoor dining area'),
  ('Outdoor', 'Outdoor furniture'),
  ('Outdoor', 'Patio or balcony'),
  ('Parking and facilities', 'Elevator'),
  ('Parking and facilities', 'EV charger'),
  (
    'Parking and facilities',
    'Free parking on premises'
  ),
  ('Parking and facilities', 'Free street parking'),
  ('Parking and facilities', 'Gym'),
  ('Parking and facilities', 'Hot tub'),
  (
    'Parking and facilities',
    'Paid parking off premises'
  ),
  (
    'Parking and facilities',
    'Paid parking on premises'
  ),
  ('Parking and facilities', 'Pool'),
  ('Parking and facilities', 'Sauna'),
  ('Parking and facilities', 'Single level home'),
  ('Services', 'Breakfast'),
  ('Services', 'Cleaning before checkout'),
  ('Services', 'Long-term stays allowed'),
  ('Services', 'Luggage drop-off allowed'),
  ('Services', 'Self check-in'),
  ('Services', 'Pets allowed'),
  ('Services', 'Lockbox');

insert into
  ListingAmenity (listing_id, amenity_name)
values
  (1, 'Coffee maker'),
  (1, 'Breakfast'),
  (1, 'Dryer'),
  (1, 'Pool'),
  (1, 'Clothing storage'),
  (1, 'Board games'),
  (1, 'Gym'),
  (1, 'Fire pit'),
  (2, 'Dining table'),
  (2, 'Sauna'),
  (2, 'Cleaning products'),
  (2, 'Outdoor furniture'),
  (2, 'Lockbox'),
  (2, 'BBQ grill'),
  (3, 'Dedicated workspace'),
  (3, 'Breakfast'),
  (3, 'Refrigerator'),
  (3, 'Mosquito net'),
  (3, 'Dishes and silverware'),
  (3, 'Coffee maker'),
  (4, 'Dining table'),
  (4, 'Hair dryer'),
  (4, 'Cleaning products'),
  (4, 'Coffee maker'),
  (5, 'Dining table'),
  (5, 'Hair dryer'),
  (5, 'Cleaning products'),
  (5, 'Coffee maker'),
  (6, 'Dining table'),
  (6, 'Hair dryer'),
  (6, 'Cleaning products'),
  (6, 'Coffee maker'),
  (6, 'Fire pit'),
  (7, 'Kayak'),
  (7, 'BBQ grill'),
  (7, 'Fire pit'),
  (7, 'Game console'),
  (7, 'Piano'),
  (7, 'Table corner guards'),
  (7, 'Hot water');

insert into
  Period (listing_id, price, start_date, end_date)
values
  (1, 149.99, '2023-09-03', '2023-09-05'),
  (1, 199.99, '2023-11-03', '2023-11-05'),
  (1, 129.99, '2023-01-03', '2023-01-05'),
  (2, 399.99, '2023-11-03', '2023-11-05'),
  (3, 599.99, '2024-12-03', '2024-12-25'),
  (3, 150.00, '2023-08-06', '2024-11-30'),
  (4, 204.49, '2023-11-03', '2023-11-05'),
  (5, 179.89, '2024-11-05', '2024-11-10'),
  (6, 999.47, '2024-11-07', '2024-11-09'),
  (7, 259.00, '2023-11-10', '2024-11-29');

insert into
  Bookings (
    id,
    status,
    start_date,
    end_date,
    listing_id,
    renter_id,
    price
  )
values
  (
    1002,
    'Booked',
    '2023-07-03',
    '2023-07-05',
    1,
    3,
    159.99
  ),
  (
    1003,
    'Booked',
    '2023-08-07',
    '2023-08-11',
    1,
    2,
    99.99
  ),
  (
    1004,
    'Cancelled',
    '2023-09-03',
    '2023-09-05',
    1,
    2,
    149.99
  ),
  (
    1005,
    'Booked',
    '2024-12-01',
    '2024-12-02',
    3,
    5,
    399.99
  ),
  (
    1006,
    'Booked',
    '2023-11-06',
    '2024-11-05',
    4,
    2,
    49.99
  ),
  (
    1007,
    'Cancelled',
    '2024-11-05',
    '2024-11-08',
    5,
    2,
    179.89
  );

insert into
  PaymentInfo (
    id,
    name_on_card,
    card_number,
    postal_code,
    expiry_date,
    user_id
  )
values
  (
    1,
    'Viktoras Margita',
    '4532900742420862',
    '60022',
    '2029-12-31',
    2
  ),
  (
    2,
    'Fionn Christa',
    '4916072661860647',
    '43567',
    '2026-10-31',
    3
  ),
  (
    3,
    'Jaci Aislin',
    '4485502104978722',
    'G1V 3V5',
    '2026-08-31',
    5
  ),
  (
    4,
    'Subramanian Kishore',
    '4916500939369488',
    'T5J 3N6',
    '2029-12-31',
    1
  );

insert into
  UserReview (
    id,
    rating,
    comment,
    booking_id,
    reviewer_id,
    reviewed_id
  )
values
  (
    20001,
    5.0,
    'Beautiful experience. Loved the host.',
    1002,
    3,
    1
  ),
  (
    20002,
    2.5,
    'Bad host, does not pick up calls.',
    1003,
    2,
    1
  ),
  (
    20003,
    5.0,
    'Respectful renter, very co-operative.',
    1002,
    1,
    3
  ),
  (
    20004,
    5.0,
    'Very picky renter, never renting again!',
    1003,
    1,
    2
  );

insert into
  ListingReview (
    id,
    rating,
    comment,
    booking_id,
    renter_id,
    listing_id
  )
values
  (15001, 4.8, 'Beautiful location.', 1002, 3, 1),
  (
    15002,
    2.0,
    'Draining roof and noisy neighbours.',
    1003,
    2,
    1
  );