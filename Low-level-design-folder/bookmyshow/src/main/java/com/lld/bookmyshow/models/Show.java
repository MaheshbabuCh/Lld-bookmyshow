package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Show {
    private String duration;
    private Movie movie;
    private ShowStatus status;
    private Auditorium auditorium;
    private Date startTime;
    private Date endTime;
    private List<Feature> features;
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