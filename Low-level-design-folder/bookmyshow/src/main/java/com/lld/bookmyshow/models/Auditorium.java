package com.lld.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{

    private String name;

    @OneToMany
    private List<Seat> seats;

//    @OneToOne
//    private Show show;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
}
