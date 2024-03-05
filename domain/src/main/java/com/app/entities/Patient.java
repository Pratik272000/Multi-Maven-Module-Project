package com.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String gender;
    String bloodGroup;
    int age;
    String contact;
    @OneToOne(cascade = CascadeType.ALL)
    Address address;

    public Patient(String name, String gender, String bloodGroup, int age, String contact) {
        this.name = name;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.contact = contact;
    }
}
