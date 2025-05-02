package com.jag.clothingApp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "garment_for_tailoring")
@Data
public class GarmentForTailoring {

    @Id
    @Column(name = "garment_garment_id")
    private Long garmentGarmentId;

    @Column(name ="tailoring_notes")
    private String tailoringNotes;

    //Relacion con la tabla garment en la cual la PK de esta tabla es una FK apuntado a la PK de garment

    @OneToOne
    @MapsId
    @JoinColumn(name = "garment_garment_id")
    private Garment garment;
}
