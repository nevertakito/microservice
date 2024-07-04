package com.example.Person.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private int id;

    String firstname;
    String surname;
    String lastname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate birthday;
    String location;

    public Person(Person person){
        this.firstname = person.getFirstname();
        this.surname = person.getSurname();
        this.lastname = person.getLastname();
        this.birthday = person.getBirthday();
        this.location = person.getLocation();
    }
}