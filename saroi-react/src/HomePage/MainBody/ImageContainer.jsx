function ImageContainer({ imageUrl }) {
  return (
    <div className="result-image-container">
      <img src={imageUrl} alt="Result" className="result-image" />
    </div>
  );
}

export default ImageContainer;
