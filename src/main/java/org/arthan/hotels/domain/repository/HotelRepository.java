package org.arthan.hotels.domain.repository;

import org.arthan.hotels.domain.entity.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, String> {

}
