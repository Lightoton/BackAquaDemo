package com.aquaOlsberg.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Double price2h;
    private Double price3h;
    private Double priceDay;


}
