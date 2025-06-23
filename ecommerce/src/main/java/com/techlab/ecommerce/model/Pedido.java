// src/main/java/com/techlab/ecommerce/model/Pedido.java

package com.techlab.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime; // Para la fecha y hora del pedido
import java.util.List; // Para la lista de LineaPedido en el Pedido

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Este constructor puedes ajustarlo si necesitas uno específico sin la lista de líneas
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muchos pedidos pueden ser de un solo cliente
    @JoinColumn(name = "cliente_id", nullable = false) // Columna para el ID del cliente
    private Cliente cliente; // El cliente que realizó el pedido

    private LocalDateTime fechaPedido; // Fecha y hora en que se realizó el pedido

    private double total; // El total del pedido

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    // Un pedido tiene muchas líneas de pedido.
    // 'mappedBy' indica que la relación es controlada por el campo 'pedido' en LineaPedido.
    // 'cascade = CascadeType.ALL' significa que si eliminas un Pedido, sus LineaPedido también se eliminarán.
    // 'orphanRemoval = true' ayuda a limpiar las LineaPedido si se desvinculan del Pedido.
    private List<LineaPedido> lineasPedido;

    // Puedes agregar más atributos como estado del pedido, dirección de envío, etc.

    // Constructor para facilitar la creación de Pedidos
    public Pedido(Cliente cliente, LocalDateTime fechaPedido, double total) {
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.total = total;
    }
}