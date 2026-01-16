export default function ImageCard({ image }) {
  const API_BASE = import.meta.env.VITE_API_BASE_URL;
  return (
    <div className="image-card">
      <img
        src={`${API_BASE}/api/images/${image.imageId}`}
        alt={image.caption}
        className="image-img"
      />
      <div className="image-overlay">
        {image.caption}
      </div>
    </div>
  );
}
