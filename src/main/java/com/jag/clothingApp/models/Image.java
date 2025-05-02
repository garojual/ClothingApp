package com.jag.clothingApp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "image_url")
    private String imageUrl;

    //Optional relationship with table outfit
    @ManyToOne(optional = true)
    @JoinColumn(name = "outfit_outfit_id")
    private Outfit outfit;

    //Obligatory relationship with table user
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

    //Optional relationship with table garment
    @ManyToOne(optional = true)
    @JoinColumn(name = "garment_garment_id")
    private Garment garment;

}
