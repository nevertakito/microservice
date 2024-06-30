package com.example.Location.controller;

import com.example.Location.model.Location;
import com.example.Location.model.Weather;
import com.example.Location.repository.LocationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.StreamSupport;


@RestController
public class LocationController {

    @Autowired
    private LocationRepository repository;
    @Autowired
    public RestTemplate restTemplate;

    /*@GetMapping
    public List<Location> getLocationList(@RequestParam(required = false) final String name) {
        if (name != null) {
            if (repository.findByName(name).isPresent())return Collections.singletonList(repository.
                    findByName(name).get());
            return null;
        } else {
            return Collections.singletonList((Location) repository.findAll());
        }
    }*/

    @GetMapping("/location/")
    public Iterable<Location> getIterableLocation() {
        return repository.findAll();
    }
    @GetMapping("/location")
    public Optional<Location> getLocation(@RequestParam String name){
        return repository.findByName(name);
    }

    @PostMapping("/location")
    public Location save(@RequestBody Location location) {
        return repository.save(location);
    }

    @PutMapping("/location")
    public Location put(@RequestParam String name, @RequestBody Location location){
        if(repository.findByName(name).isPresent()){
            Location loc = repository.findByName(name).get();
            loc.setName(location.getName());
            loc.setLatitude(location.getLatitude());
            loc.setLongitude(location.getLongitude());
            return repository.save(loc);
        }
        return null;
    }

    @DeleteMapping("/location")
    public void delete(@RequestParam String name){
        if(repository.findByName(name).isPresent()) repository.delete(repository.findByName(name).get());
    }

    @GetMapping("/location/weather")
    public Weather redirectRequestWeather(@RequestParam String name) throws JsonProcessingException {
        Location location;
        if(repository.findByName(name).isPresent()){
            location = repository.findByName(name).get();
        }
        else{
            return null;
        }

        String url = String.format("http://localhost:8082/weather?lat=%s&lon=%s", location.getLatitude(),
                location.getLongitude());
        String o = restTemplate.getForObject(url, String.class);

        Map<String, Object> respMap = jsonToMap(o);
        Map<String, Object> weatherMain = (Map<String, Object>) respMap.get("main");

        Weather weather = new Weather(weatherMain);
        return weather;
    }

    public Map<String, Object> jsonToMap(String str) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> map = objectMapper.readValue(str, typeReference);
        return map;
    }
}