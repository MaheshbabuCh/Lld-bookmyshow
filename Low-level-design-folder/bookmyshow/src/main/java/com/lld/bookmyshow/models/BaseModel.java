package com.lld.bookmyshow.models;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.Id;
@MappedSuperclass
public class BaseModel {
    @Id
    private String id;
    private String createdAt;
    private String updatedAt;
}
