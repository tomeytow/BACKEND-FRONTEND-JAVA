package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.dto.ProductoDTO;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.mapper.ProductoMapper;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos()
            .stream()
            .map(ProductoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return ResponseEntity.ok(ProductoMapper.toDTO(producto));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO,
        @RequestHeader(value = "X-ROLE", required = false) String role) {
        if (!"admin".equals(role)) return ResponseEntity.status(403).build();
        Producto producto = ProductoMapper.toEntity(productoDTO);
        Producto nuevoProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(ProductoMapper.toDTO(nuevoProducto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id,
        @RequestHeader(value = "X-ROLE", required = false) String role) {
        if (!"admin".equals(role)) return ResponseEntity.status(403).build();
        if (!productoService.obtenerProductoPorId(id).isPresent())
            throw new ResourceNotFoundException("Producto no encontrado");
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}