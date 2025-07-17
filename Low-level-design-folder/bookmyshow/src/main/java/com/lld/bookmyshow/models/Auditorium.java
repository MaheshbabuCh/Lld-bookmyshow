package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Auditorium {

    private String name;
    private List<Seat> seats;
    private Show show;
    private List<Feature> features;
}
