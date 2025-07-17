package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Movie {
    private String title;
    private String description;
    private String duration;
    private List<Language> languages;
    private String details;
}
