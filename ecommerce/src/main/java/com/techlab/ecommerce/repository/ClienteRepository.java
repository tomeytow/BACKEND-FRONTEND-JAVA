// src/main/java/com/techlab.ecommerce.repository/ClienteRepository.java

package com.techlab.ecommerce.repository;

import com.techlab.ecommerce.model.Cliente; // Importamos la clase Cliente
import org.springframework.data.jpa.repository.JpaRepository; // Importamos JpaRepository
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Igual que con Producto, tenemos métodos básicos para Cliente.
}