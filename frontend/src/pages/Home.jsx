import { useState, useRef } from "react";
import { searchImages, uploadImages } from "../api/imageApi";
import ImageGrid from "../components/ImageGrid";
import logo from "../assets/saroi-logo.png";

const LIMIT = 8;

export default function Home() {
  const [query, setQuery] = useState("");
  const [images, setImages] = useState([]);
  const [offset, setOffset] = useState(0);
  const [hasMore, setHasMore] = useState(false);
  const [uploading, setUploading] = useState(false);
  const [searching, setSearching] = useState(false);


  const fileInputRef = useRef(null);

  const runSearch = async (reset = true) => {
  if (!query.trim()) return;

  setSearching(true);

    try {
      const res = await searchImages(query, reset ? 0 : offset, LIMIT);

      console.log("RAW RESPONSE:", res);
      console.log("RAW DATA:", res?.data);

      const data = Array.isArray(res?.data) ? res.data : [];

      console.log("FINAL DATA USED:", data);

      if (reset) {
        setImages(data);
        setOffset(LIMIT);
      } else {
        setImages(prev => [...prev, ...data]);
        setOffset(prev => prev + LIMIT);
      }

      setHasMore(data.length === LIMIT);
    } finally {
      setSearching(false);
    }
  };



  const handleUploadClick = () => {
    fileInputRef.current.click();
  };

  const handleFilesSelected = async (e) => {
    const files = [...e.target.files];
    if (!files.length) return;

    setUploading(true);
    await uploadImages(files);
    setUploading(false);
  };

  return (
    <>
      {/* TOP BAR */}
      <div className="topbar">
        <div className="brand">
          <img src={logo} alt="SAROI" className="logo" />
        </div>

        <div className="search-container">
          <input
            className="search-box"
            placeholder="Search images..."
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && runSearch(true)}
          />
        </div>

        <button
          className="button"
          onClick={handleUploadClick}
          disabled={uploading}
        >
          {uploading ? "Uploading…" : "Upload"}
        </button>

        <input
          type="file"
          multiple
          hidden
          ref={fileInputRef}
          onChange={handleFilesSelected}
        />
      </div>

      {/* MAIN CONTENT */}
      <div className="main">
        <ImageGrid images={images} loading={searching} />

        {hasMore && (
          <div className="load-more">
            <button className="button" onClick={() => runSearch(false)}>
              Load more
            </button>
          </div>
        )}
      </div>
    </>
  );
}
