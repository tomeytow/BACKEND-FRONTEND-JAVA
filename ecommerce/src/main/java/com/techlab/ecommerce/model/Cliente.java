// src/main/java/com/techlab/ecommerce/model/Cliente.java

package com.techlab.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity // Indica que esta clase es una tabla en la base de datos
public class Cliente {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-generado
    private Long id;

    @Column(nullable = false) // Nombre del cliente, no puede ser nulo
    private String nombre;

    @Column(nullable = false, unique = true) // Email del cliente, no puede ser nulo y debe ser único
    private String email;

    @Column // Dirección del cliente (puede ser nula)
    private String direccion;

    // --- Constructores ---
    public Cliente() {
        // Constructor vacío
    }

    public Cliente(String nombre, String email, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}