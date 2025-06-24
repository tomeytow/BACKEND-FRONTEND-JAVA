import React, { useEffect, useState } from "react";
import { useAuth, AuthProvider } from "./AuthContext";
import AdminPanel from "./AdminPanel";

function LoginForm() {
  const { login } = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [err, setErr] = useState("");

  function handleSubmit(e) {
    e.preventDefault();
    fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password })
    })
      .then(res => {
        if (!res.ok) throw new Error("Login incorrecto");
        return res.json();
      })
      .then(data => {
        login(data);
      })
      .catch(e => setErr(e.message));
  }

  return (
    <form onSubmit={handleSubmit}>
      <input type="email" placeholder="Email" value={email} required onChange={e => setEmail(e.target.value)} />
      <input type="password" placeholder="Password" value={password} required onChange={e => setPassword(e.target.value)} />
      <button type="submit">Iniciar sesión</button>
      {err && <p style={{ color: "red" }}>{err}</p>}
    </form>
  );
}

function Productos() {
  const [productos, setProductos] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/api/productos")
      .then(res => res.json())
      .then(setProductos);
  }, []);
  return (
    <div>
      <h2>Productos</h2>
      <ul>
        {productos.map(p => (
          <li key={p.id}>{p.nombre} - ${p.precio} - Stock: {p.stock}</li>
        ))}
      </ul>
    </div>
  );
}

function Pedidos() {
  const [pedidos, setPedidos] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/api/pedidos")
      .then(res => res.json())
      .then(setPedidos);
  }, []);
  return (
    <div>
      <h2>Mis Pedidos</h2>
      <ul>
        {pedidos.map(ped => (
          <li key={ped.id}>Pedido #{ped.id} - Productos: {ped.lineasPedido.map(lp => `(${lp.productoId} x ${lp.cantidad})`).join(", ")}</li>
        ))}
      </ul>
    </div>
  );
}

function HacerPedido() {
  const [productoId, setProductoId] = useState("");
  const [cantidad, setCantidad] = useState(1);
  const [mensaje, setMensaje] = useState("");
  const { user } = useAuth();

  function hacerPedido(e) {
    e.preventDefault();
    fetch("http://localhost:8080/api/pedidos", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        clienteId: user?.userId,
        lineasPedido: [{ productoId: Number(productoId), cantidad: Number(cantidad) }]
      })
    })
      .then(res => {
        if (res.ok) return res.json();
        return res.json().then(data => { throw new Error(data.message); });
      })
      .then(() => setMensaje("Pedido realizado con éxito"))
      .catch(err => setMensaje("Error: " + err.message));
  }

  if (!user) return <p>Inicia sesión para hacer pedidos.</p>;

  return (
    <form onSubmit={hacerPedido}>
      <input type="number" placeholder="ID Producto" value={productoId} onChange={e => setProductoId(e.target.value)} required />
      <input type="number" placeholder="Cantidad" min={1} value={cantidad} onChange={e => setCantidad(e.target.value)} required />
      <button type="submit">Hacer Pedido</button>
      <p>{mensaje}</p>
    </form>
  );
}

function AppContent() {
  const { user, logout } = useAuth();

  return (
    <div>
      <h1>Ecommerce Moderno</h1>
      {user ?
        <>
          <p>Bienvenido, {user.role === "admin" ? "admin" : "usuario"} <button onClick={logout}>Cerrar sesión</button></p>
          {user.role === "admin" && <AdminPanel />}
          <Productos />
          <HacerPedido />
          <Pedidos />
        </>
        :
        <LoginForm />
      }
    </div>
  );
}

function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;