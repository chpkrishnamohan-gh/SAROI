import ImageCard from "./ImageCard";

export default function ImageGrid({ images, loading }) {
  // 1️⃣ Loading state
  if (loading) {
    return (
      <div className="status-text">
        Searching…
      </div>
    );
  }

  // 2️⃣ Empty result state
  if (!Array.isArray(images) || images.length === 0) {
    return (
      <div className="status-text">
        No images found
      </div>
    );
  }

  // 3️⃣ Normal render
  return (
    <div className="masonry">
      {images.map((img) => (
        <ImageCard key={img.imageId} image={img} />
      ))}
    </div>
  );
}
