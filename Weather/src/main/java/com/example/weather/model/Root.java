package com.example.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Root {
    @Id
    @JsonIgnore
    private int ids;
    @OneToOne(cascade = CascadeType.ALL)
    private Coord coord;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Weather> weather;
    private String base;
    @OneToOne(cascade = CascadeType.ALL)
    private Main main;
    private int visibility;
    @OneToOne(cascade = CascadeType.ALL)
    private Wind wind;
    @OneToOne(cascade = CascadeType.ALL)
    private Clouds clouds;
    private int dt;
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private Sys sys;
    private int timezone;
    private String name;
    private int cod;
}
