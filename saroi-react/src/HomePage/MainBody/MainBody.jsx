import ImageContainer from "./ImageContainer.jsx";

function MainBody({ ImageUrls }) {
  return (
    <div className="main-body">
      {ImageUrls.map((url, i) => (
        <div key={i} className="image-wrapper">
          <ImageContainer imageUrl={url} />
        </div>
      ))}
    </div>
  );
}

export default MainBody;
