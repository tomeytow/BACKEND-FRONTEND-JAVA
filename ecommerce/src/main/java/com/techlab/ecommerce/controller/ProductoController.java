@PostMapping
public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO,
    @RequestHeader(value = "X-ROLE", required = false) String role) {
    if (!"admin".equals(role)) return ResponseEntity.status(403).build();
    Producto producto = ProductoMapper.toEntity(productoDTO);
    Producto nuevoProducto = productoService.crearProducto(producto);
    return new ResponseEntity<>(ProductoMapper.toDTO(nuevoProducto), HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarProducto(@PathVariable Long id,
    @RequestHeader(value = "X-ROLE", required = false) String role) {
    if (!"admin".equals(role)) return ResponseEntity.status(403).build();
    if (!productoService.obtenerProductoPorId(id).isPresent())
        throw new ResourceNotFoundException("Producto no encontrado");
    productoService.eliminarProducto(id);
    return ResponseEntity.noContent().build();
}