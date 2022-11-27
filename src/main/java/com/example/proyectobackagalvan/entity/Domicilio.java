package com.example.proyectobackagalvan.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Domicilios")
@Getter @Setter
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String calle;
    @Column(nullable = false)
    private Integer numero;
    @Column(nullable = false)
    private String localidad;
    @Column(nullable = false)
    private String provincia;
}
