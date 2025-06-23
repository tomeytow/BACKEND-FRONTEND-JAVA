// src/main/java/com/techlab/ecommerce/model/Producto.java

package com.techlab.ecommerce.model;

import jakarta.persistence.Entity; // Anotación para indicar que es una entidad de JPA
import jakarta.persistence.GeneratedValue; // Anotación para generación automática de IDs
import jakarta.persistence.GenerationType; // Tipo de estrategia para la generación de IDs
import jakarta.persistence.Id; // Anotación para indicar que es la clave primaria
import jakarta.persistence.Column; // Anotación para mapear a una columna en la base de datos
import jakarta.validation.constraints.NotBlank; // Importa la anotación NotBlank para validación
import jakarta.validation.constraints.Min; // Importa la anotación Min para validación

@Entity // Le dice a Spring que esta clase es una tabla en la base de datos
public class Producto {

    @Id // Marca este campo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a la base de datos que genere automáticamente el ID
    private Long id; // El identificador único del producto

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false) // Mapea el campo 'nombre' a una columna en la base de datos y no permite valores nulos
    private String nombre; // El nombre del producto

    @Column(nullable = false) // Mapea el campo 'precio' y no permite valores nulos
    private double precio; // El precio del producto

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false) // Mapea el campo 'stock' y no permite valores nulos
    private int stock; // La cantidad de productos disponibles

    public Producto() {
        // Constructor vacío: Necesario para que JPA pueda crear instancias de Producto
    }

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // --- Getters y Setters ---
    // Los "getters" nos permiten obtener el valor de un atributo.
    // Los "setters" nos permiten cambiar el valor de un atributo.

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}