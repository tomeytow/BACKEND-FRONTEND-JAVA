// src/main/java/com/techlab/ecommerce/service/ProductoService.java

package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;
import java.util.List;
import java.util.Optional; // Para manejar casos donde un producto puede no existir

public interface ProductoService {
    Producto crearProducto(Producto producto); // Guarda un nuevo producto
    Optional<Producto> obtenerProductoPorId(Long id); // Busca un producto por su ID
    List<Producto> obtenerTodosLosProductos(); // Obtiene todos los productos
    Producto actualizarProducto(Long id, Producto productoDetalles); // Actualiza un producto existente
    void eliminarProducto(Long id); // Elimina un producto por su ID
}