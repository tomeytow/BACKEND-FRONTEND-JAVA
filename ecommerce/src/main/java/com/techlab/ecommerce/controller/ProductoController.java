// src/main/java/com/techlab/ecommerce/controller/ProductoController.java

package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService; // Importamos el servicio
import org.springframework.http.HttpStatus; // Para códigos de estado HTTP
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP
import org.springframework.web.bind.annotation.*; // Anotaciones para mapeo de solicitudes
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin; // Para manejar CORS (Cross-Origin Resource Sharing)
import jakarta.validation.Valid; // Importa la anotación @Valid



@RestController // Indica a Spring que esta clase es un controlador REST
@RequestMapping("/api/productos") // Todas las rutas en este controlador comenzarán con /api/productos
@CrossOrigin(origins = "*") // O puedes poner "*" para permitir desde cualquier origen para desarrollo
public class ProductoController {

    private final ProductoService productoService; // Declaramos el servicio

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // --- Métodos para manejar las solicitudes HTTP ---

    // POST /api/productos
    // Crea un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        // @RequestBody convierte el JSON de la solicitud en un objeto Producto
        Producto nuevoProducto = productoService.crearProducto(producto);
        // ResponseEntity permite controlar el código de estado HTTP (201 Creado)
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    // GET /api/productos/{id}
    // Obtiene un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        // @PathVariable extrae el ID de la URL
        return productoService.obtenerProductoPorId(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK)) // Si lo encuentra, devuelve 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Si no lo encuentra, devuelve 404 Not Found
    }

    // GET /api/productos
    // Obtiene todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK); // Devuelve 200 OK
    }

    // PUT /api/productos/{id}
    // Actualiza un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        Producto productoActualizado = productoService.actualizarProducto(id, productoDetalles);
        if (productoActualizado != null) {
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK); // Si se actualizó, devuelve 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si no lo encontró, devuelve 404 Not Found
        }
    }

    // DELETE /api/productos/{id}
    // Elimina un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 No Content (eliminación exitosa sin contenido de respuesta)
    }
}