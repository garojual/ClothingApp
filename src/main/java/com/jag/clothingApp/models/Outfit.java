package com.jag.clothingApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "outfit")
@Data
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outfit_id")
    private Long outfitId;

    @Column(name = "outfit_name")
    private String outfitName;

    @Column(name = "outfit_notes")
    private String outfitNotes;

    //Obligatory relationship with table user
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "outfit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> iamges = new ArrayList<>();
}
