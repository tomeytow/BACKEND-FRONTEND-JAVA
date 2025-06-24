import React, { useState } from 'react';

function ProductCard({ product, user }) {
  const [msg, setMsg] = useState('');
  const [qty, setQty] = useState(1);

  const handleOrder = async () => {
    setMsg('');
    try {
      const res = await fetch('http://localhost:8080/api/pedidos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          productoId: product.id,
          cantidad: qty,
          usuarioEmail: user.email
        }),
      });
      if (res.ok) {
        setMsg('¡Pedido realizado con éxito!');
      } else {
        setMsg('Error al realizar el pedido');
      }
    } catch {
      setMsg('No se pudo conectar al servidor');
    }
  };

  return (
    <div className="card h-100 shadow-sm">
      <img
        src={product.imagenUrl || '/placeholder-instrumento.jpg'}
        alt={product.nombre}
        className="card-img-top"
        style={{ maxHeight: 180, objectFit: 'cover', background: '#eee' }}
      />
      <div className="card-body d-flex flex-column">
        <h5 className="card-title">{product.nombre}</h5>
        <p className="card-text">{product.descripcion}</p>
        <div className="mt-auto">
          <div className="fw-bold fs-5 mb-2 text-primary">${product.precio}</div>
          {user && (
            <div className="input-group mb-2">
              <input
                type="number"
                min="1"
                className="form-control"
                value={qty}
                style={{ maxWidth: 70 }}
                onChange={e => setQty(Number(e.target.value))}
              />
              <button className="btn btn-success" onClick={handleOrder}>
                Comprar
              </button>
            </div>
          )}
          {msg && <div className="alert alert-info py-1">{msg}</div>}
        </div>
      </div>
    </div>
  );
}

export default ProductCard;