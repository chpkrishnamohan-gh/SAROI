import axios from "axios";

const API = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: {
    "ngrok-skip-browser-warning": "true",
  },
});

export const searchImages = (query, offset, limit) =>
  API.get("/api/images/search", {
    params: { query, offset, limit },
  });

export const uploadImages = (files) => {
  const form = new FormData();
  files.forEach(f => form.append("files", f));

  return API.post("/api/images/batch-upload", form);
};
