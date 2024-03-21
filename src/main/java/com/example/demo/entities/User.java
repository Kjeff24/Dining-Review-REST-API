package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "USERS")
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, name = "username")
    @JsonProperty("username")
    @NotEmpty
    private String displayName;

    private String city;
    private String state;
    private String zipCode;

    private Boolean peanutAllergy;
    private Boolean dairyAllergy;
    private Boolean eggAllergy;



}
