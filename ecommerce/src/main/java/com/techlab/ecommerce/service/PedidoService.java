package com.techlab.ecommerce.service;

import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido) throws StockInsuficienteException;
    List<Pedido> obtenerTodosLosPedidos();
    Pedido obtenerPedidoPorId(Long id);
}