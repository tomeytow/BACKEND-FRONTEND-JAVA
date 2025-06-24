package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    Optional<Cliente> obtenerClientePorId(Long id);
    List<Cliente> obtenerTodosLosClientes();
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);

    // Para login
    boolean checkPassword(String email, String password);
    Cliente findByEmail(String email);
}