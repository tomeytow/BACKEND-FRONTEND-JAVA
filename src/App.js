import React, { useState } from 'react';
import Header from './components/Header';
import PromoBanner from './components/PromoBanner';
import LoginSidebar from './components/LoginSidebar';
import ProductGallery from './components/ProductGallery';
import Footer from './components/Footer';
import './App.css';

function App() {
  const [user, setUser] = useState(null);

  return (
    <div className="app-bg">
      <Header />
      <PromoBanner />
      <div className="container-fluid my-4">
        <div className="row">
          <div className="col-md-3 mb-4">
            <LoginSidebar user={user} setUser={setUser} />
          </div>
          <div className="col-md-9">
            <ProductGallery user={user} />
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default App;