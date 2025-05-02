package com.jag.clothingApp.services;

import com.jag.clothingApp.models.Garment;
import com.jag.clothingApp.repositories.GarmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GarmentService {

    @Autowired
    private GarmentRepository garmentRepository;

    public Garment getGarmentById(Long id){
        return garmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Garment not found"));
    }
}
