package com.app.dao;

import com.app.entities.AccessData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<AccessData,Integer> {
    AccessData findByEmail(String email);
}
