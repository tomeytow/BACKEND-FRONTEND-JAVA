package com.techlab.ecommerce.mapper;

import com.techlab.ecommerce.dto.ClienteDTO;
import com.techlab.ecommerce.model.Cliente;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setEmail(c.getEmail());
        dto.setDireccion(c.getDireccion());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        c.setDireccion(dto.getDireccion());
        return c;
    }
}