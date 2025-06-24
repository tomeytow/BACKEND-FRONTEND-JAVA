document.addEventListener('DOMContentLoaded', () => {
    const listaProductos = document.getElementById('lista-productos');
    const formPedido = document.getElementById('form-pedido');
    const mensajePedido = document.getElementById('mensaje-pedido');
    const listaPedidos = document.getElementById('lista-pedidos');

    fetch('http://localhost:8080/api/productos')
        .then(res => res.json())
        .then(data => {
            listaProductos.innerHTML = '';
            data.forEach(p => {
                const div = document.createElement('div');
                div.className = 'producto-item';
                div.innerHTML = `
                    <h3>${p.nombre}</h3>
                    <p><strong>Descripción:</strong> ${p.descripcion}</p>
                    <p><strong>Precio:</strong> $${p.precio}</p>
                    <p><strong>ID:</strong> ${p.id}</p>
                `;
                listaProductos.appendChild(div);
            });
        });

    formPedido.addEventListener('submit', e => {
        e.preventDefault();
        const productoId = document.getElementById('productoId').value;
        const cantidad = document.getElementById('cantidad').value;

        fetch('http://localhost:8080/api/pedidos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ productoId, cantidad })
        })
        .then(res => res.json())
        .then(data => {
            mensajePedido.textContent = 'Pedido realizado correctamente';
            cargarPedidos();
        })
        .catch(err => {
            mensajePedido.textContent = 'Error al realizar pedido';
        });
    });

    function cargarPedidos() {
        fetch('http://localhost:8080/api/pedidos')
            .then(res => res.json())
            .then(data => {
                listaPedidos.innerHTML = '';
                data.forEach(pedido => {
                    const div = document.createElement('div');
                    div.className = 'pedido-item';
                    div.innerHTML = `
                        <h3>Pedido #${pedido.id}</h3>
                        <p><strong>Fecha:</strong> ${pedido.fecha}</p>
                        <ul>
                            ${pedido.items.map(i => `<li>${i.producto.nombre} x ${i.cantidad}</li>`).join('')}
                        </ul>
                    `;
                    listaPedidos.appendChild(div);
                });
            });
    }

    cargarPedidos();
});