package com.jag.clothingApp.controllers;

import com.jag.clothingApp.models.Garment;
import com.jag.clothingApp.services.GarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/garments")
public class GarmentController {

    GarmentService garmentService;

    @Autowired
    public GarmentController(GarmentService garmentService){
        this.garmentService = garmentService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Garment> getGarmentById(@PathVariable Long id){
        Garment garment = garmentService.getGarmentById(id);
        return ResponseEntity.ok(garment);
    }



}
