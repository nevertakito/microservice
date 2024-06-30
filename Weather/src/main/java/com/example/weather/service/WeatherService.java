package com.example.weather.service;

import com.example.weather.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    public RestTemplate restTemplate;
    @Value("${appid}")
    private String appId;
    @Value("${url.weather}")
    private String urlWeather;
    @Cacheable(cacheNames = {"weatherCache"}, key="{#lat, #lon}")
    public Root getWeather(String lat, String lon){
        String request = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s",
                urlWeather, lat, lon, appId);
        return restTemplate.getForObject(request, Root.class);
    }

    @CachePut(cacheNames = {"weatherCache"}, key="{#lat, #lon}")
    public Root createOrUpdateWeather(String lat, String lon){
        String request = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s",
                urlWeather, lat, lon, appId);
        return restTemplate.getForObject(request, Root.class);
    }
}
