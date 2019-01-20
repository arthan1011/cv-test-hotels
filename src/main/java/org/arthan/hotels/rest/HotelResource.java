package org.arthan.hotels.rest;

import org.arthan.hotels.rest.model.HotelDTO;
import org.arthan.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping(value = "/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HotelDTO> getHotel(@PathVariable("hotelId") String id) {
        Optional<HotelDTO> foundHotel = hotelService.findHotel(id);

        if (foundHotel.isPresent()) {
            return ResponseEntity.ok(foundHotel.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{hotelId}")
    void deleteHotel(@PathVariable("hotelId") String id) {
        hotelService.removeHotel(id);
    }

    @PutMapping(value = "/{hotelId}")
    void updateHotel(@PathVariable("hotelId") String id, @RequestBody HotelDTO data) {
        hotelService.updateHotel(id, data);
    }
}
