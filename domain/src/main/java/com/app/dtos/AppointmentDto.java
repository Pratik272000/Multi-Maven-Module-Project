package com.app.dtos;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {
    String patientName;
    String doctorName;
    LocalDateTime date;

}
