package com.techlab.ecommerce.service;

import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.LineaPedido; // Importar LineaPedido
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto; // Importar Producto
import com.techlab.ecommerce.repository.PedidoRepository; // Importar PedidoRepository
import com.techlab.ecommerce.repository.ProductoRepository; // Importar ProductoRepository

import org.springframework.stereotype.Service; // Importar Service
import jakarta.transaction.Transactional; // Importar Transactional

import java.util.List;

@Service // ¡IMPORTANTE! Indica a Spring que esta es una clase de servicio
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    // Constructor para inyección de dependencias (forma recomendada)
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional // Asegura que toda la operación sea transaccional (o todo se guarda, o nada)
    public Pedido crearPedido(Pedido nuevoPedido) throws StockInsuficienteException {
        // Validar que el pedido tenga al menos una línea
        if (nuevoPedido.getLineasPedido() == null || nuevoPedido.getLineasPedido().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe contener al menos una línea de pedido.");
        }

        // Iterar sobre cada línea del pedido para validar stock y actualizar productos
        for (LineaPedido linea : nuevoPedido.getLineasPedido()) {
            // Validar que la línea tenga un producto y un ID de producto
            if (linea.getProducto() == null || linea.getProducto().getId() == null) {
                throw new IllegalArgumentException("Cada línea de pedido debe referenciar un producto existente.");
            }

            // Buscar el producto en la base de datos para obtener su stock actual
            // ¡CORRECCIÓN AQUÍ!: Debería ser productoRepository.findById, no pedidoRepository.findById
            Producto producto = productoRepository.findById(linea.getProducto().getId())
                .orElseThrow(() -> new StockInsuficienteException("Producto con ID " + linea.getProducto().getId() + " no encontrado."));

            // Verificar si hay suficiente stock
            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre() + ". Stock disponible: " + producto.getStock() + ", cantidad solicitada: " + linea.getCantidad());
            }

            // Descontar la cantidad solicitada del stock del producto
            producto.setStock(producto.getStock() - linea.getCantidad());
            // Guardar el producto con el stock actualizado en la base de datos
            productoRepository.save(producto);

            // Importante: Asignar el objeto 'Producto' que JPA está gestionando
            // a la LineaPedido para asegurar la persistencia correcta.
            linea.setProducto(producto);
            // Establecer la relación bidireccional entre LineaPedido y Pedido
            linea.setPedido(nuevoPedido);
        }

        // Una vez que todas las líneas y stocks han sido validados y actualizados,
        // guardar el pedido. Las líneas de pedido se guardarán en cascada
        // si tienes configurado CascadeType.ALL o CascadeType.PERSIST en la relación @OneToMany en Pedido.
        return pedidoRepository.save(nuevoPedido);
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPedidoPorId(Long id) {
        // Usa Optional para manejar el caso de que el pedido no exista
        return pedidoRepository.findById(id).orElse(null);
    }
}