import React from 'react';

function Footer() {
  return (
    <footer className="bg-dark text-white py-3 mt-4">
      <div className="container text-center">
        <small>© {new Date().getFullYear()} AG Percusión · Todos los derechos reservados.</small>
      </div>
    </footer>
  );
}

export default Footer;