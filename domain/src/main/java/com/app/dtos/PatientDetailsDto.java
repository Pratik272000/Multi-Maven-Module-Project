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


}
