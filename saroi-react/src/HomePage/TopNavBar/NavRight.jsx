function NavRight() {
  return (
    <div className="nav-right">
        <button className="nav-btn">STORE NEW IMAGES</button>
        <button className="nav-btn" onClick={() => window.location.href='stored_images_page.html'}>STORED IMAGES</button>
    </div>
  );
}

export default NavRight;