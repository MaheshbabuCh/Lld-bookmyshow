package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class City {
    private List<Theatre> theatres;
    private String name;
    private String zipCode;
}
