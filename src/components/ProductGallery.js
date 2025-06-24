import React, { useEffect, useState } from 'react';
import ProductCard from './ProductCard';

function ProductGallery({ user }) {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [msg, setMsg] = useState('');

  useEffect(() => {
    setLoading(true);
    fetch('http://localhost:8080/api/productos')
      .then(res => res.ok ? res.json() : [])
      .then(data => {
        setProducts(Array.isArray(data) ? data : []);
        setLoading(false);
      })
      .catch(() => {
        setMsg('No se pudo cargar la galería de productos.');
        setLoading(false);
      });
  }, []);

  return (
    <section id="galeria">
      <h2 className="fw-bold mb-4">Instrumentos disponibles</h2>
      {loading && <div className="text-center py-4">Cargando productos...</div>}
      {msg && <div className="alert alert-danger">{msg}</div>}
      <div className="row g-4">
        {products.map(product => (
          <div className="col-12 col-sm-6 col-md-4 col-lg-3" key={product.id}>
            <ProductCard product={product} user={user} />
          </div>
        ))}
      </div>
      {!loading && products.length === 0 && (
        <div className="text-center py-4 text-muted">
          No hay instrumentos cargados.
        </div>
      )}
    </section>
  );
}

export default ProductGallery;