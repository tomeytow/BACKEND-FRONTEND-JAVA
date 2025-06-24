package com.techlab.ecommerce.mapper;

import com.techlab.ecommerce.dto.ProductoDTO;
import com.techlab.ecommerce.model.Producto;

public class ProductoMapper {
    public static ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        return dto;
    }

    public static Producto toEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        return p;
    }
}