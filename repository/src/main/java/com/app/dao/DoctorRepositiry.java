package com.app.dao;

import com.app.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepositiry extends JpaRepository<Doctor,Integer> {
}
