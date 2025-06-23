package com.techlab.ecommerce.repository;

import com.techlab.ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Puedes añadir métodos personalizados aquí si necesitas buscar pedidos por usuario, por ejemplo.
    // List<Pedido> findByUsuarioId(Long usuarioId); // Si tuvieras una entidad Usuario
}