// src/main/java/com/techlab/ecommerce/service/ClienteServiceImpl.java

package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Cliente;
import com.techlab.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(clienteDetalles.getNombre());
            cliente.setEmail(clienteDetalles.getEmail());
            cliente.setDireccion(clienteDetalles.getDireccion());
            return clienteRepository.save(cliente);
        }).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}