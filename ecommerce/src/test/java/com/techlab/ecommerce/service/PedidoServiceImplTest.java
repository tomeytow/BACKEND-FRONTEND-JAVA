package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.*;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceImplTest {
    @Test
    void crearPedido_conStockSuficiente() {
        PedidoRepository pedidoRepo = mock(PedidoRepository.class);
        ProductoRepository prodRepo = mock(ProductoRepository.class);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setStock(10);
        when(prodRepo.findById(1L)).thenReturn(Optional.of(producto));

        LineaPedido linea = new LineaPedido();
        linea.setProducto(producto);
        linea.setCantidad(2);

        Pedido pedido = new Pedido();
        pedido.setLineasPedido(Collections.singletonList(linea));

        when(pedidoRepo.save(any(Pedido.class))).thenReturn(pedido);

        PedidoServiceImpl service = new PedidoServiceImpl(pedidoRepo, prodRepo);
        Pedido result = service.crearPedido(pedido);

        assertNotNull(result);
        verify(prodRepo).save(producto);
        verify(pedidoRepo).save(pedido);
    }
}