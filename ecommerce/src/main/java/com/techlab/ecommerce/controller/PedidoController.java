package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.dto.PedidoDTO;
import com.techlab.ecommerce.dto.LineaPedidoDTO;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.mapper.PedidoMapper;
import com.techlab.ecommerce.model.*;
import com.techlab.ecommerce.service.ClienteService;
import com.techlab.ecommerce.service.PedidoService;
import com.techlab.ecommerce.service.ProductoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    @Autowired
    public PedidoController(PedidoService pedidoService, ClienteService clienteService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        if (pedidoDTO == null || pedidoDTO.getClienteId() == null || pedidoDTO.getLineasPedido() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Cliente cliente = clienteService.obtenerClientePorId(pedidoDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        List<Producto> productos = pedidoDTO.getLineasPedido().stream()
                .map(ldto -> productoService.obtenerProductoPorId(ldto.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + ldto.getProductoId())))
                .collect(Collectors.toList());
        Pedido pedido = PedidoMapper.toEntity(pedidoDTO, cliente, productos);
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(PedidoMapper.toDTO(nuevoPedido), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        if (pedido == null) throw new ResourceNotFoundException("Pedido no encontrado");
        return ResponseEntity.ok(PedidoMapper.toDTO(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> obtenerTodosLosPedidos() {
        List<PedidoDTO> dtos = pedidoService.obtenerTodosLosPedidos()
                .stream().map(PedidoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}