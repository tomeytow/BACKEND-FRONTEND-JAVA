package com.techlab.ecommerce.mapper;

import com.techlab.ecommerce.dto.PedidoDTO;
import com.techlab.ecommerce.dto.LineaPedidoDTO;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {
    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setClienteId(pedido.getCliente() != null ? pedido.getCliente().getId() : null);
        dto.setLineasPedido(
            pedido.getLineasPedido().stream().map(linea -> {
                LineaPedidoDTO lDto = new LineaPedidoDTO();
                lDto.setProductoId(linea.getProducto() != null ? linea.getProducto().getId() : null);
                lDto.setCantidad(linea.getCantidad());
                return lDto;
            }).collect(Collectors.toList())
        );
        return dto;
    }

    public static Pedido toEntity(PedidoDTO dto, Cliente cliente, List<Producto> productos) {
        Pedido pedido = new Pedido();
        pedido.setId(dto.getId());
        pedido.setCliente(cliente);
        List<LineaPedido> lineas = dto.getLineasPedido().stream().map(ldto -> {
            LineaPedido lp = new LineaPedido();
            lp.setProducto(productos.stream()
                .filter(p -> p.getId().equals(ldto.getProductoId()))
                .findFirst().orElse(null)
            );
            lp.setCantidad(ldto.getCantidad());
            lp.setPedido(pedido);
            return lp;
        }).collect(Collectors.toList());
        pedido.setLineasPedido(lineas);
        return pedido;
    }
}