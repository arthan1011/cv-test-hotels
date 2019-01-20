CREATE TABLE hotel
(
  id VARCHAR(12) PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
  catid varchar(12),
  addr VARCHAR(256) NOT NULL,
  img VARCHAR(256),
  first_coordinate FLOAT,
  second_coordinate FLOAT,
  site_label VARCHAR(128),
  site_url VARCHAR(128),
  services VARCHAR(512)
);
CREATE UNIQUE INDEX hotel_hotel_id_uindex ON hotel (id);