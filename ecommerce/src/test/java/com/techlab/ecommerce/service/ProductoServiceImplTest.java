package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class ProductoServiceImplTest {
    @Test
    void crearProducto() {
        ProductoRepository repo = mock(ProductoRepository.class);
        ProductoServiceImpl service = new ProductoServiceImpl(repo);

        Producto producto = new Producto();
        producto.setNombre("Bateria");
        when(repo.save(any(Producto.class))).thenReturn(producto);

        Producto guardado = service.crearProducto(producto);
        assertEquals("Bateria", guardado.getNombre());
        verify(repo).save(producto);
    }

    @Test
    void obtenerProductoPorId() {
        ProductoRepository repo = mock(ProductoRepository.class);
        ProductoServiceImpl service = new ProductoServiceImpl(repo);

        Producto producto = new Producto();
        producto.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(producto));
        Optional<Producto> result = service.obtenerProductoPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
}