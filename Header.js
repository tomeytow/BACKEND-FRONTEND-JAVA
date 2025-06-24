import React from 'react';
import './Header.css';

function Header() {
  return (
    <header className="header">
      <img src="/logo-ag-percusion.png" alt="AG Percusión" className="logo" />
      <h1>AG PERCUSION</h1>
      <nav>
        {/* Puedes agregar más links aquí */}
      </nav>
    </header>
  );
}

export default Header;