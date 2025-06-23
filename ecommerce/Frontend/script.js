document.addEventListener('DOMContentLoaded', () => {
    const BASE_URL = 'http://localhost:8080/api';

    // Referencias a elementos del DOM
    const listaProductosDiv = document.getElementById('lista-productos');
    const formPedido = document.getElementById('form-pedido');
    const productoIdInput = document.getElementById('productoId');
    const cantidadInput = document.getElementById('cantidad');
    const mensajePedidoP = document.getElementById('mensaje-pedido');
    const listaPedidosDiv = document.getElementById('lista-pedidos');

    // Función para obtener y mostrar productos
    async function obtenerYMostrarProductos() {
        try {
            const response = await fetch(`${BASE_URL}/productos`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const productos = await response.json();
            listaProductosDiv.innerHTML = ''; // Limpiar lista
            if (productos.length === 0) {
                listaProductosDiv.innerHTML = '<p>No hay productos disponibles.</p>';
                return;
            }
            productos.forEach(producto => {
                const productoItem = document.createElement('div');
                productoItem.className = 'producto-item';
                productoItem.innerHTML = `
                    <h3>${producto.nombre} (ID: ${producto.id})</h3>
                    <p>Precio: $${producto.precio.toFixed(2)}</p>
                    <p>Stock: ${producto.stock}</p>
                `;
                listaProductosDiv.appendChild(productoItem);
            });
        } catch (error) {
            listaProductosDiv.innerHTML = `<p style="color: red;">Error al cargar productos: ${error.message}</p>`;
            console.error('Error al obtener productos:', error);
        }
    }

    // Función para obtener y mostrar pedidos
    async function obtenerYMostrarPedidos() {
        try {
            const response = await fetch(`${BASE_URL}/pedidos`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const pedidos = await response.json();
            listaPedidosDiv.innerHTML = ''; // Limpiar lista
            if (pedidos.length === 0) {
                listaPedidosDiv.innerHTML = '<p>No hay pedidos realizados aún.</p>';
                return;
            }
            pedidos.forEach(pedido => {
                const pedidoItem = document.createElement('div');
                pedidoItem.className = 'pedido-item';
                let lineasHtml = pedido.lineasPedido.map(lp => 
                    `<li>${lp.cantidad} x ${lp.producto ? lp.producto.nombre : 'Producto Desconocido'}</li>`
                ).join('');
                pedidoItem.innerHTML = `
                    <h3>Pedido ID: ${pedido.id}</h3>
                    <p>Fecha: ${new Date(pedido.fechaCreacion).toLocaleDateString()}</p>
                    <h4>Items:</h4>
                    <ul>${lineasHtml}</ul>
                `;
                listaPedidosDiv.appendChild(pedidoItem);
            });
        } catch (error) {
            listaPedidosDiv.innerHTML = `<p style="color: red;">Error al cargar pedidos: ${error.message}</p>`;
            console.error('Error al obtener pedidos:', error);
        }
    }
// Example of a frontend fetch call (assuming it's similar)
// This is JavaScript, not Java.
async function hacerPedido() {
    const productId = document.getElementById('productId').value;
    const quantity = document.getElementById('quantity').value;

    const data = {
        productoId: productId, // Make sure these match your backend DTO/entity fields
        cantidad: quantity
        // Add any other necessary fields for creating a Pedido or LineaPedido
    };

    try {
        const response = await fetch('http://localhost:8080/api/pedidos', { // Or /api/lineas-pedido depending on your design
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        // Check if the response is OK (2xx status)
        if (response.ok) {
            const result = await response.json(); // THIS IS WHERE THE "Unexpected token 'P'" ERROR OCCURS
            console.log('Pedido realizado con éxito:', result);
            alert('Pedido realizado con éxito!');
            // Optionally, refresh orders list
            cargarPedidos();
        } else {
            // IMPORTANT: Handle non-200 responses
            const errorText = await response.text(); // Get response as plain text first
            console.error('Error al realizar el pedido:', response.status, errorText);

            // Try to parse as JSON if possible (e.g., validation errors)
            try {
                const errorJson = JSON.parse(errorText);
                console.error('Detalles del error JSON:', errorJson);
                alert('Error al realizar pedido: ' + JSON.stringify(errorJson));
            } catch (e) {
                // If it's not JSON, display the plain text
                alert('Error al realizar pedido: ' + errorText);
            }
        }
    } catch (error) {
        console.error('Error de conexión:', error.message);
        alert('Error de conexión: ' + error.message);
    }
}
    // Manejar el envío del formulario de pedido
    formPedido.addEventListener('submit', async (e) => {
        e.preventDefault(); // Evitar que el formulario se recargue

        const productoId = parseInt(productoIdInput.value);
        const cantidad = parseInt(cantidadInput.value);

        const pedidoData = {
            lineasPedido: [
                {
                    producto: {
                        id: productoId
                    },
                    cantidad: cantidad
                }
            ]
        };

        try {
            const response = await fetch(`${BASE_URL}/pedidos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pedidoData)
            });

            const result = await response.json(); // Intentar leer como JSON siempre

            if (response.ok) {
                mensajePedidoP.style.color = 'green';
                mensajePedidoP.textContent = `Pedido realizado con éxito! ID: ${result.id}`;
                formPedido.reset(); // Limpiar formulario
                obtenerYMostrarProductos(); // Actualizar lista de productos (stock)
                obtenerYMostrarPedidos(); // Actualizar lista de pedidos
            } else {
                mensajePedidoP.style.color = 'red';
                // Si el backend devuelve un mensaje de error en el JSON
                mensajePedidoP.textContent = `Error al realizar pedido: ${result.message || JSON.stringify(result)}`;
            }
        } catch (error) {
            mensajePedidoP.style.color = 'red';
            mensajePedidoP.textContent = `Error de conexión: ${error.message}`;
            console.error('Error al realizar pedido:', error);
        }
    });

    // Cargar datos al iniciar la página
    obtenerYMostrarProductos();
    obtenerYMostrarPedidos();
});