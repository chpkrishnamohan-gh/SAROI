import { useState } from "react";
import { uploadImages } from "../api/imageApi";

export default function UploadPanel() {
  const [files, setFiles] = useState([]);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleUpload = async () => {
        if (files.length === 0) return;

        setLoading(true);
        setResult(null);

        try {
            const res = await uploadImages(files);
            setResult(res.data);
        } catch (err) {
            console.error("Upload failed:", err);
            alert("Upload failed. Check console.");
        } finally {
            setLoading(false);
        }
    };


  return (
    <div style={{ display: "flex", gap: 16, alignItems: "center" }}>
      <input
        type="file"
        multiple
        onChange={(e) => setFiles([...e.target.files])}
      />
      <button onClick={handleUpload} disabled={loading}>
        {loading ? "Uploading…" : "Upload"}
      </button>
    </div>

  );
}
