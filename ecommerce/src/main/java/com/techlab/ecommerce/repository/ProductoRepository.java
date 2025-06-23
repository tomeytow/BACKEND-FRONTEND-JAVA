// src/main/java/com/techlab/ecommerce/repository/ProductoRepository.java

package com.techlab.ecommerce.repository;

import com.techlab.ecommerce.model.Producto; // Importamos la clase Producto
import org.springframework.data.jpa.repository.JpaRepository; // Importamos JpaRepository
import org.springframework.stereotype.Repository; // Anotación opcional, pero buena práctica

@Repository // Indica a Spring que esta interfaz es un componente de repositorio
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository ya nos da métodos básicos como save(), findById(), findAll(), deleteById(), etc.
    // <Producto, Long> significa que trabajará con la entidad Producto y su ID es de tipo Long.
}