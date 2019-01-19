package org.arthan.hotels.service;

import org.arthan.hotels.domain.entity.Hotel;
import org.arthan.hotels.domain.repository.HotelRepository;
import org.arthan.hotels.rest.model.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String addHotel(HotelDTO newHotel) {

        Hotel hotelEntity = new Hotel();

        hotelEntity.address = newHotel.getAddr();
        hotelEntity.image = newHotel.getImg();
        hotelEntity.catalogId = newHotel.getCatid();
        hotelEntity.name = newHotel.getName();


        Hotel savedHotel = hotelRepository.save(hotelEntity);

        return savedHotel.id;
    }
}
