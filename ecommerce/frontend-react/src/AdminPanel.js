import React, { useEffect, useState } from "react";
import { useAuth } from "./AuthContext";

export default function AdminPanel() {
  const [productos, setProductos] = useState([]);
  const [nombre, setNombre] = useState("");
  const [precio, setPrecio] = useState("");
  const [stock, setStock] = useState("");
  const [mensaje, setMensaje] = useState("");
  const { user } = useAuth();

  function fetchProductos() {
    fetch("http://localhost:8080/api/productos")
      .then(res => res.json())
      .then(setProductos);
  }

  useEffect(() => { fetchProductos(); }, []);

  function agregarProducto(e) {
    e.preventDefault();
    fetch("http://localhost:8080/api/productos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-ROLE": user.role
      },
      body: JSON.stringify({ nombre, precio: parseFloat(precio), stock: parseInt(stock) })
    })
      .then(res => {
        if (!res.ok) throw new Error("No autorizado o error");
        return res.json();
      })
      .then(() => {
        setMensaje("Producto agregado!");
        fetchProductos();
      })
      .catch(e => setMensaje(e.message));
  }

  function eliminarProducto(id) {
    fetch(`http://localhost:8080/api/productos/${id}`, {
      method: "DELETE",
      headers: { "X-ROLE": user.role }
    })
      .then(res => {
        if (!res.ok) throw new Error("No autorizado o error");
        fetchProductos();
      })
      .catch(e => setMensaje(e.message));
  }

  return (
    <div>
      <h2>Panel de administración</h2>
      <form onSubmit={agregarProducto}>
        <input type="text" placeholder="Nombre" value={nombre} onChange={e => setNombre(e.target.value)} required />
        <input type="number" placeholder="Precio" value={precio} onChange={e => setPrecio(e.target.value)} required />
        <input type="number" placeholder="Stock" value={stock} onChange={e => setStock(e.target.value)} required />
        <button type="submit">Agregar producto</button>
      </form>
      <p>{mensaje}</p>
      <ul>
        {productos.map(p => (
          <li key={p.id}>
            {p.nombre} - ${p.precio} - Stock: {p.stock}
            <button onClick={() => eliminarProducto(p.id)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
}