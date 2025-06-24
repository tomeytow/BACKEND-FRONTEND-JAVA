import React, { useState } from 'react';
import './LoginSidebar.css';

function LoginSidebar({ user, setUser }) {
  // login logic aquí
  // Si el usuario está logueado, mostrar "Hola, [nombre]"
  // Si no, mostrar el formulario
  // Es opcional: la galería siempre se puede ver

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = e => {
    e.preventDefault();
    // Aquí va tu lógica de login (fetch al backend)
    // Si éxito: setUser({email: ...})
    // Si error: mostrar mensaje
  };

  return (
    <aside className="login-sidebar">
      {user ? (
        <div>
          <p>Hola, {user.email}</p>
          <button onClick={() => setUser(null)}>Cerrar sesión</button>
        </div>
      ) : (
        <form onSubmit={handleLogin}>
          <h2>Iniciar sesión</h2>
          <input
            type="email"
            placeholder="Correo"
            value={email}
            onChange={e => setEmail(e.target.value)}
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
          <button type="submit">Entrar</button>
        </form>
      )}
    </aside>
  );
}

export default LoginSidebar;