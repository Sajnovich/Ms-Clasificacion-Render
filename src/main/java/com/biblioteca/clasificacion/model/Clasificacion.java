package com.biblioteca.clasificacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clasificaciones")
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String pegi;

    @Column(nullable = false, length = 20)
    private String esrb;

    @Column(name = "edad_minima", nullable = false)
    private Integer edadMinima;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;
}
