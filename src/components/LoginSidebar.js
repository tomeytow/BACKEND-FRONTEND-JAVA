import React, { useState } from 'react';

function LoginSidebar({ user, setUser }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [msg, setMsg] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setMsg('');
    try {
      const res = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
      });
      if (res.ok) {
        const data = await res.json();
        setUser(data);
        setEmail('');
        setPassword('');
      } else {
        setMsg('Login incorrecto');
      }
    } catch (err) {
      setMsg('No se pudo conectar al servidor');
    }
  };

  const handleLogout = () => {
    setUser(null);
    setMsg('');
  };

  return (
    <div className="card shadow-sm">
      <div className="card-body">
        {user ? (
          <div className="text-center">
            <h5 className="mb-3">¡Hola, {user.email}!</h5>
            <button className="btn btn-outline-danger btn-sm" onClick={handleLogout}>Cerrar sesión</button>
          </div>
        ) : (
          <form onSubmit={handleLogin}>
            <h5 className="mb-3 text-center">Iniciar sesión</h5>
            <div className="mb-2">
              <input
                type="email"
                className="form-control"
                placeholder="Correo"
                value={email}
                onChange={e => setEmail(e.target.value)}
                required
              />
            </div>
            <div className="mb-2">
              <input
                type="password"
                className="form-control"
                placeholder="Contraseña"
                value={password}
                onChange={e => setPassword(e.target.value)}
                required
              />
            </div>
            <div className="d-grid mb-2">
              <button type="submit" className="btn btn-primary">Entrar</button>
            </div>
            {msg && <div className="alert alert-danger py-1">{msg}</div>}
            <p className="small text-muted mt-2 mb-0 text-center">
              El inicio de sesión es opcional.<br />Puedes explorar la galería sin registrarte.
            </p>
          </form>
        )}
      </div>
    </div>
  );
}

export default LoginSidebar;