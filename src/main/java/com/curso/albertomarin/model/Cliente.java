package com.curso.albertomarin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter @Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;

    @Column(name = "direccion", nullable = true, length = 150)
    private String direccion;

    @Column(name = "telefono",  nullable = true, length = 9)
    private String telefono;

    @Column(name = "email", nullable = true, length = 55)
    private String email;

}
