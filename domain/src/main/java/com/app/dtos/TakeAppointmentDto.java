package com.app.dtos;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TakeAppointmentDto {
    int patientId;
    int doctorId;
    LocalDateTime localDateTime;
}
