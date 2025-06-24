package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Cliente;
import com.techlab.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
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
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> encontrado = clienteRepository.findById(id);
        if (encontrado.isPresent()) {
            Cliente existente = encontrado.get();
            existente.setNombre(cliente.getNombre());
            existente.setApellido(cliente.getApellido());
            existente.setEmail(cliente.getEmail());
            existente.setPassword(cliente.getPassword());
            // agrega aquí el resto de campos que tenga Cliente
            return clienteRepository.save(existente);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean checkPassword(String email, String password) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null || cliente.getPassword() == null) {
            return false;
        }
        return cliente.getPassword().equals(password);
    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}