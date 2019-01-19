package org.arthan.hotels.rest;

import org.arthan.hotels.rest.model.HotelDTO;
import org.arthan.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
public class HotelResource {

    final private HotelService hotelService;

    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    String addHotel(@RequestBody HotelDTO newHotel) {

        return hotelService.addHotel(newHotel);
    }
}
