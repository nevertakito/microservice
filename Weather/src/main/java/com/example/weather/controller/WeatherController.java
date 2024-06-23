package com.example.weather.controller;

import com.example.weather.model.Root;
import com.example.weather.service.WeatherService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController

public class WeatherController {
    private final WeatherService service;
    public WeatherController(WeatherService service) {
        this.service = service;
    }
    private final List<LocalTime> arrayTime = new ArrayList<>();
    private int i = 0;

    @GetMapping("/weather")
    public Root getWeather(@RequestParam String lat, @RequestParam String lon) {
        arrayTime.add(LocalTime.now());
        if(arrayTime.size()>1 && LocalTime.now().minusMinutes(1).isBefore(arrayTime.get(i-1))){
            i++;
            return service.getWeather(lat,lon);
        }
        else{
            i++;
            return service.createOrUpdateWeather(lat,lon);
        }
    }
}