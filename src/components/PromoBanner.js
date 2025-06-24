import React from 'react';
import './PromoBanner.css';

function PromoBanner() {
  return (
    <div className="promo-banner d-flex align-items-center justify-content-center mb-4">
      <div className="text-center text-white py-4 w-100">
        <h2 className="fw-bold mb-2">¡Bienvenido a AG Percusión!</h2>
        <p className="lead mb-3">
          Descubre la mejor selección de instrumentos de percusión. <br />
          <span className="fw-semibold">¡Grandes ofertas de lanzamiento! 10% de descuento en tu primera compra.</span>
        </p>
        <a href="#galeria" className="btn btn-light btn-lg fw-bold shadow-sm">Ver instrumentos</a>
      </div>
    </div>
  );
}

export default PromoBanner;