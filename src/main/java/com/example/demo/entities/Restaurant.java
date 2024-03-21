package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    private String line1;
    private String city;
    private String state;
    @NotEmpty
    private String zipCode;

    private String phoneNumber;
    private String website;

    private String overallScore;
    private String peanutScore;
    private String dairyScore;
    private String eggScore;
}
