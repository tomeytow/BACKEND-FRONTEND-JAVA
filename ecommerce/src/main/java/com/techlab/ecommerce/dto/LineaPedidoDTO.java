package com.techlab.ecommerce.dto;

public class LineaPedidoDTO {
    private Long productoId;
    private int cantidad;

    // Getters y setters
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}