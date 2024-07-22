package com.example.Person.controller;

import com.example.Person.PersonApplication;
import com.example.Person.model.Person;
import com.example.Person.model.Weather;
import com.example.Person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id) {
        if (repository.existsById(id)) {
            String location = repository.findById(id).get().getLocation();
            Weather weather = restTemplate.getForObject("http://location-service/location/weather?name=" +
                    location, Weather.class);
            return new ResponseEntity(weather, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Person put(@PathVariable int id, @RequestBody Person person){
        if(repository.findById(id).isPresent()){
            Person newPerson = repository.findById(id).orElse(new Person());
            newPerson.setFirstname(person.getFirstname());
            newPerson.setSurname(person.getSurname());
            newPerson.setLastname(person.getLastname());
            newPerson.setBirthday(person.getBirthday());
            newPerson.setLocation(person.getLocation());
            return repository.save(newPerson);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        if(repository.findById(id).isPresent()) repository.deleteById(id);
    }
}