package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido) {
        try {
            Pedido pedidoCreado = pedidoService.crearPedido(nuevoPedido);
            return new ResponseEntity<>(pedidoCreado, HttpStatus.CREATED);
        } catch (StockInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            System.err.println("Error inesperado al crear el pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error interno del servidor al crear el pedido"));
        }
    }

    @GetMapping("/someEndpoint")
    public ResponseEntity<String> getSomething(
        @RequestParam(required = false) MultiValueMap<String, String> params) {
        if (params == null) {
            params = new org.springframework.util.LinkedMultiValueMap<>();
        }
        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al obtener los pedidos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoPorId(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.obtenerPedidoPorId(id);
            if (pedido != null) {
                return new ResponseEntity<>(pedido, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Pedido no encontrado"));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el pedido por ID: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Error interno del servidor al obtener el pedido"));
        }
    }
}
