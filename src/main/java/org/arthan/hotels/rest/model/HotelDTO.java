package org.arthan.hotels.rest.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.arthan.hotels.domain.entity.HOTEL_SERVICE;

import java.util.List;
import java.util.stream.Collectors;

public class HotelDTO {

    private String id;
    private String name;
    private String catid;
    private String addr;
    private String img;
    private SiteDTO site;
    private List<HOTEL_SERVICE> services;

    private List<Double> point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Double> getPoint() {
        return point;
    }

    public void setPoint(List<Double> point) {
        this.point = point;
    }

    public SiteDTO getSite() {
        return site;
    }

    public void setSite(SiteDTO site) {
        this.site = site;
    }

    @JsonGetter
    public List<String> getServices() {
        if (services == null) {
            return null;
        }
        return services.stream().map(s -> s.name().toLowerCase()).collect(Collectors.toList());
    }

    public void setServices(List<HOTEL_SERVICE> services) {
        this.services = services;
    }
}
