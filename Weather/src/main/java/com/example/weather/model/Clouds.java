package com.example.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Clouds{
    @Id
    @JsonIgnore
    private int ids;
    @Column(name = "max")
    private int all;
}