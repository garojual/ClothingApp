package com.jag.clothingApp.repositories;

import com.jag.clothingApp.models.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutfitRepository extends JpaRepository<Outfit, Long> {
}
