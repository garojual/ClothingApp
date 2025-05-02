package com.jag.clothingApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "garment")
@Data
public class Garment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "garment_id")
    private Long garmentId;

    @Column(name = "garment_name")
    private String garmentName;

    @Column(name = "garment_desc")
    private String garmentDesc;

    @Column(name = "garment_color")
    private String garmentColor;

    @Column(name = "is_bought")
    private Boolean isBought;

    @Column(name = "is_for_tailoring")
    private Boolean isForTailoring;

    //Relaciones 1 to 0-1 con las tablas garment_for_buy y garment_for_tailoring

    @OneToOne(mappedBy = "garment", cascade = CascadeType.ALL)
    private GarmentForBuy garmentForBuy;

    @OneToOne(mappedBy = "garment", cascade = CascadeType.ALL)
    private GarmentForTailoring garmentForTailoring;

    @OneToMany(mappedBy = "garment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    //Obligatory relationship with table user
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

}
