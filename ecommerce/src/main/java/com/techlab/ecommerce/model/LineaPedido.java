package com.techlab.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LineaPedido implements java.io.Serializable {

    public LineaPedido() {
        // JPA requires a no-argument constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muchas líneas de pedido para un mismo pedido
    @JoinColumn(name = "pedido_id") // La columna en la DB que guarda el ID del pedido
    private Pedido pedido;

    @ManyToOne // Muchas líneas de pedido para un mismo producto
    @JoinColumn(name = "producto_id") // La columna en la DB que guarda el ID del producto
    private Producto producto;

    private int cantidad;
    private double precioUnitario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaPedido that = (LineaPedido) o;
        return java.util.Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
}