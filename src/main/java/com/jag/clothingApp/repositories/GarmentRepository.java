package com.jag.clothingApp.repositories;

import com.jag.clothingApp.models.Garment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarmentRepository extends JpaRepository<Garment, Long> {
}
