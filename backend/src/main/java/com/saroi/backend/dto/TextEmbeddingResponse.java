package com.saroi.backend.dto;

import java.util.List;

public class TextEmbeddingResponse {

    private List<Float> embedding;

    public List<Float> getEmbedding() {
        return embedding;
    }

    public void setEmbedding(List<Float> embedding) {
        this.embedding = embedding;
    }
}
