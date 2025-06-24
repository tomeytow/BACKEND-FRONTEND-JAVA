package com.techlab.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.techlab.ecommerce.dto.ProductoDTO;
import com.techlab.ecommerce.mapper.ProductoMapper;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET /api/productos
    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return productos.stream().map(ProductoMapper::toDTO).collect(Collectors.toList());
    }

    // POST /api/productos
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO,
        @RequestHeader(value = "X-ROLE", required = false) String role) {
        if (!"admin".equals(role)) return ResponseEntity.status(403).build();
        Producto producto = ProductoMapper.toEntity(productoDTO);
        Producto nuevoProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(ProductoMapper.toDTO(nuevoProducto), HttpStatus.CREATED);
    }

    // DELETE /api/productos/{id}
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