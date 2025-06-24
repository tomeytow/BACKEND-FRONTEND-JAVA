package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.dto.ClienteDTO;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.mapper.ClienteMapper;
import com.techlab.ecommerce.model.Cliente;
import com.techlab.ecommerce.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(ClienteMapper.toDTO(nuevoCliente), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return ResponseEntity.ok(ClienteMapper.toDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> dtos = clienteService.obtenerTodosLosClientes()
                .stream().map(ClienteMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente actualizado = clienteService.actualizarCliente(id, ClienteMapper.toEntity(clienteDTO));
        if (actualizado == null) throw new ResourceNotFoundException("Cliente no encontrado");
        return ResponseEntity.ok(ClienteMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (!clienteService.obtenerClientePorId(id).isPresent())
            throw new ResourceNotFoundException("Cliente no encontrado");
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}