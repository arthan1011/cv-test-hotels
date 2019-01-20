package org.arthan.hotels.domain.entity;

import org.arthan.hotels.domain.Identifiable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel implements Identifiable {

    @Id
    @GenericGenerator(
            name = "hotel-generator",
            strategy = "org.arthan.hotels.domain.HotelIdentityGenerator"
    )
    @GeneratedValue(generator = "hotel-generator")
    public String id;

    @Column
    public String name;

    @Column(name = "catid")
    public String catalogId;

    @Column(name = "addr")
    public String address;

    @Column(name = "img")
    public String image;

    @Column(name = "first_coordinate")
    public Double firstCoordinate;

    @Column(name = "second_coordinate")
    public Double secondCoordinate;

    @Column(name = "site_label")
    public String siteLabel;

    @Column(name = "site_url")
    public String siteUrl;

    @Column
    public String services;

}
