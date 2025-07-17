package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Theatre {
    private String name;
    private List<Auditorium> auditorium;
    private  String address;
}
