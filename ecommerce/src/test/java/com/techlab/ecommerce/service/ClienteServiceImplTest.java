package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Cliente;
import com.techlab.ecommerce.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Test
    void crearCliente() {
        ClienteRepository repo = mock(ClienteRepository.class);
        ClienteServiceImpl service = new ClienteServiceImpl(repo);

        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        when(repo.save(any(Cliente.class))).thenReturn(cliente);

        Cliente guardado = service.crearCliente(cliente);
        assertEquals("Juan", guardado.getNombre());
        verify(repo).save(cliente);
    }

    @Test
    void obtenerClientePorId() {
        ClienteRepository repo = mock(ClienteRepository.class);
        ClienteServiceImpl service = new ClienteServiceImpl(repo);

        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(cliente));
        Optional<Cliente> result = service.obtenerClientePorId(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
}