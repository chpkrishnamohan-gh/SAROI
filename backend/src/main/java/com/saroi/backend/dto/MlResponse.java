package com.saroi.backend.dto;

import java.util.List;

public class MlResponse {
    private String caption;
    private List<Float> embedding;

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public List<Float> getEmbedding() { return embedding; }
    public void setEmbedding(List<Float> embedding) { this.embedding = embedding; }
}
