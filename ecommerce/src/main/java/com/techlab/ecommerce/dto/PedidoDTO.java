package com.techlab.ecommerce.dto;

import java.util.List;

public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private List<LineaPedidoDTO> lineasPedido;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<LineaPedidoDTO> getLineasPedido() { return lineasPedido; }
    public void setLineasPedido(List<LineaPedidoDTO> lineasPedido) { this.lineasPedido = lineasPedido; }
}