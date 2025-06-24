import React from 'react';

function Header() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow">
      <div className="container">
        <a className="navbar-brand d-flex align-items-center" href="/">
          <img src="/logo-ag-percusion.png" alt="AG Percusión Logo" width="48" height="48" className="me-2 rounded" />
          <span className="fw-bold fs-3">AG PERCUSION</span>
        </a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>
        {/* Puedes agregar más links o menús aquí */}
      </div>
    </nav>
  );
}

export default Header;