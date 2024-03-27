package com.app.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDetailsDto {
    String name;
    String gender;
    String bloodGroup;
    int age;
    String contact;
    String city;
    String lane;
    String pincode;


    public PatientDetailsDto(String name, String gender, String bloodGroup, int age, String contact) {
        this.name = name;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.contact = contact;
    }
}
