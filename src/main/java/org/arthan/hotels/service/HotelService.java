package org.arthan.hotels.service;

import org.arthan.hotels.domain.entity.HOTEL_SERVICE;
import org.arthan.hotels.domain.entity.Hotel;
import org.arthan.hotels.domain.repository.HotelRepository;
import org.arthan.hotels.rest.model.HotelDTO;
import org.arthan.hotels.rest.model.SiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String addHotel(HotelDTO newHotel) {

        Hotel hotelEntity = toHotelEntity(newHotel);

        Hotel savedHotel = hotelRepository.save(hotelEntity);

        return savedHotel.id;
    }

    public Optional<HotelDTO> findHotel(String id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);

        if (hotel.isPresent()) {
            Hotel foundHotel = hotel.get();

            HotelDTO result = new HotelDTO();
            result.setCatid(foundHotel.catalogId);
            result.setId(foundHotel.id);
            result.setAddr(foundHotel.address);
            result.setImg(foundHotel.image);
            result.setName(foundHotel.name);

            List<Double> pointsList = new ArrayList<>();
            pointsList.add(foundHotel.firstCoordinate);
            pointsList.add(foundHotel.secondCoordinate);
            result.setPoint(pointsList);

            SiteDTO siteDTO = new SiteDTO();
            siteDTO.setLabel(foundHotel.siteLabel);
            siteDTO.setUrl(foundHotel.siteUrl);
            result.setSite(siteDTO);

            if (foundHotel.services != null) {
                List<HOTEL_SERVICE> serviceList = Arrays.stream(foundHotel.services.split(","))
                        .map(String::toUpperCase)
                        .map(HOTEL_SERVICE::valueOf)
                        .collect(Collectors.toList());
                result.setServices(serviceList);
            }

            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    public void removeHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    public void updateHotel(String hotelId, HotelDTO data) {
        Hotel hotelEntity = toHotelEntity(data);
        hotelEntity.id = hotelId;

        hotelRepository.save(hotelEntity);
    }

    private Hotel toHotelEntity(HotelDTO newHotel) {
        Hotel hotelEntity = new Hotel();

        hotelEntity.address = newHotel.getAddr();
        hotelEntity.image = newHotel.getImg();
        hotelEntity.catalogId = newHotel.getCatid();
        hotelEntity.name = newHotel.getName();
        hotelEntity.firstCoordinate = newHotel.getPoint() != null ? newHotel.getPoint().get(0) : null;
        hotelEntity.secondCoordinate = newHotel.getPoint() != null ? newHotel.getPoint().get(1) : null;
        hotelEntity.siteLabel = newHotel.getSite() != null ? newHotel.getSite().getLabel() : null;
        hotelEntity.siteUrl = newHotel.getSite() != null ?newHotel.getSite().getUrl() : null;
        hotelEntity.services = newHotel.getServices() != null ? String.join(",", newHotel.getServices()) : null;
        return hotelEntity;
    }
}
