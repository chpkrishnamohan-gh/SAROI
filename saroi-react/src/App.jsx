import NavBar from './HomePage/TopNavBar/NavBar.jsx';
import MainBody from './HomePage/MainBody/MainBody.jsx';

function App() {
  const modules = import.meta.glob("/UserData/StoredImages/*", { eager: true });
  const ImageUrls = Object.values(modules).map(m => m.default);

  return (
    <div>
      <NavBar />
      <MainBody ImageUrls={ImageUrls} />
    </div>
  );
}

export default App;
