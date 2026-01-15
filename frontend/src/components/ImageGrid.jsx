import ImageCard from "./ImageCard";

export default function ImageGrid({ images }) {
  return (
    <div className="masonry">
      {images.map(img => (
        <ImageCard key={img.imageId} image={img} />
      ))}
    </div>
  );
}
