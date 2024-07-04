package com.example.Person.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Weather {
    @Id
    @JsonIgnore
    private int ids;
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;

    public Weather(Map<String, Object> weather) {
        this.temp = (double) weather.get("temp");
        this.feels_like = (double) weather.get("feels_like");
        this.temp_min = (double) weather.get("temp_min");
        this.temp_max = (double) weather.get("temp_max");
        this.pressure = (int) weather.get("pressure");
        this.humidity = (int) weather.get("humidity");
        this.sea_level = (int) weather.get("sea_level");
        this.grnd_level = (int) weather.get("grnd_level");
    }
}