package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Auditorium {

    private String name;
    private List<Seat> seats;
    private Show show;
    private List<Feature> features;
}
