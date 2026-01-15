import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api/images",
});

export const uploadImages = (files) => {
  const form = new FormData();
  files.forEach((f) => form.append("files", f));
  return API.post("/batch-upload", form);
};

export const searchImages = (query, offset, limit) => {
  return API.get("/search", {
    params: { query, offset, limit },
  });
};
