export default function ImageCard({ image }) {
  return (
    <div className="image-card">
      <img
        src={`http://localhost:8080/api/images/${image.imageId}`}
        alt={image.caption}
        className="image-img"
      />
      <div className="image-overlay">
        {image.caption}
      </div>
    </div>
  );
}
