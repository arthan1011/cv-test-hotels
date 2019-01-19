CREATE TABLE hotel
(
  id VARCHAR(12) PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
  catid varchar(12),
  addr VARCHAR(256) NOT NULL,
  img VARCHAR(256)
);
CREATE UNIQUE INDEX hotel_hotel_id_uindex ON hotel (id);