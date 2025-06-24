import React, { useState, useEffect } from 'react';
import Header from './Header';
import LoginSidebar from './LoginSidebar';
import ProductGallery from './ProductGallery';
import './App.css';

function App() {
  const [user, setUser] = useState(null);

  return (
    <div className="app-container">
      <Header />
      <div className="main-content">
        <LoginSidebar user={user} setUser={setUser} />
        <ProductGallery user={user} />
      </div>
    </div>
  );
}

export default App;