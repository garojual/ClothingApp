package com.jag.clothingApp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "outfit_has_garment")
@Data
public class OutiftHasGarment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outfit_garment_id")
    private Long OutfitGarmentId;

    @ManyToOne
    @JoinColumn(name = "outift_outfit_id", nullable = false)
    private Outfit outfit;

    @ManyToOne
    @JoinColumn(name = "garment_garment_id", nullable = false)
    private Garment garment;


}
