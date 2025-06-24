import React from 'react';
import './ProductCard.css';

function ProductCard({ product, user }) {
  return (
    <div className="product-card">
      <img src={product.imagenUrl} alt={product.nombre} />
      <h3>{product.nombre}</h3>
      <p>${product.precio}</p>
      {/* Solo muestra botón de comprar si está logueado */}
      {user && <button>Comprar</button>}
    </div>
  );
}

export default ProductCard;