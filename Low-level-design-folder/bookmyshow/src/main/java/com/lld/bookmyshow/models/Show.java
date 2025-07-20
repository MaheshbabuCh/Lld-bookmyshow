package com.lld.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity(name = "Shows")
public class Show extends BaseModel{

    private String duration;

    @ManyToOne
    private Movie movie;

    private ShowStatus status;

    @ManyToOne
    private Auditorium auditorium;

    private Date startTime;
    private Date endTime;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> features;

    @Enumerated(EnumType.STRING)
    private Language language;
}

/*

duration
Movie
ShowStatus
Auditorium
startTime
endTime
language
List<Feature>
 */