function agregarAlCarrito(productoId) {
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const index = carrito.findIndex(p => p.productoId === productoId);
    if (index > -1) {
        carrito[index].cantidad += 1;
    } else {
        carrito.push({ productoId: productoId, cantidad: 1 });
    }
    localStorage.setItem('carrito', JSON.stringify(carrito));
    alert('Producto agregado al carrito');
}
