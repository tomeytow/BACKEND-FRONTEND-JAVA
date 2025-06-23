// src/main/java/com/techlab/ecommerce/service/ProductoServiceImpl.java

package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository; // Importamos el repositorio
import org.springframework.stereotype.Service; // Indica que es un componente de servicio
import java.util.List;
import java.util.Optional;

@Service // Le dice a Spring que esta clase es un servicio y que debe ser gestionada por él
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository; // Declaramos el repositorio


    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto); // Usamos el método save del repositorio para guardar
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id); // Usamos findById para buscar por ID
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll(); // Usamos findAll para obtener todos los productos
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoDetalles) {
        return productoRepository.findById(id).map(producto -> { // Buscamos el producto por ID
            producto.setNombre(productoDetalles.getNombre());
            producto.setPrecio(productoDetalles.getPrecio());
            producto.setStock(productoDetalles.getStock());
            return productoRepository.save(producto); // Guardamos los cambios
        }).orElse(null); // Si no encuentra el producto, devuelve null. Podrías lanzar una excepción.
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id); // Elimina el producto por ID
    }
}