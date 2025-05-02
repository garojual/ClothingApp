package com.jag.clothingApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "garment_for_buy")
@Data
public class GarmentForBuy {
    @Id
    @Column(name = "garment_garment_id")
    private Long garmentGarmentId;

    @Column(name = "buying_url")
    private String buyingUrl;

    @Column(name = "garment_size")
    private String garmentSize;

    @Column(name = "garment_price")
    private Float garmentPrice;

    //Relacion con la tabla garment en la cual la PK de esta tabla es una FK apuntado a la PK de garment

    @OneToOne
    @MapsId
    @JoinColumn(name = "garment_garment_id")
    private Garment garment;

}
