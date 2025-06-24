import React, { useEffect, useState } from 'react';
import ProductCard from './ProductCard';
import './ProductGallery.css';

function ProductGallery({ user }) {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    // Cambia la URL por la de tu backend
    fetch('http://localhost:8080/api/productos')
      .then(res => res.json())
      .then(data => setProducts(data));
  }, []);

  return (
    <section className="gallery">
      <h2>Instrumentos disponibles</h2>
      <div className="gallery-grid">
        {products.map(product => (
          <ProductCard key={product.id} product={product} user={user} />
        ))}
      </div>
    </section>
  );
}

export default ProductGallery;