package com.jag.clothingApp.repositories;

import com.jag.clothingApp.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
