import NavCenter from "./NavCenter";
import NavRight from "./NavRight";

function NavBar() {
  return (
    <nav className="navbar">
      <div className="nav-left">
        <div className="nav-logo">SAROI</div>
      </div>

      <NavCenter />
      <NavRight />
    </nav>
  );
}

export default NavBar;
