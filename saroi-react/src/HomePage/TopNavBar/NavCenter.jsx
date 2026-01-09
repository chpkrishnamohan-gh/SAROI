import searchIcon from '../../assets/Images/search-icon.png';

function NavCenter() {
  return (
    <div className="nav-center">
        <div className="search-bar">
        <input className="search-input-bar" type="text" placeholder="Describe your image..." />
        <button className="search-submit" type="submit">
            <img src={searchIcon} alt="Search" style={{ width: "20px", height: "20px", borderRadius: "50%" }}></img>
        </button>
        </div>
    </div>
  );
}

export default NavCenter;