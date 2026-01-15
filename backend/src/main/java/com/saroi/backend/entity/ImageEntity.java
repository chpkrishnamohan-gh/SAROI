package com.saroi.backend.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    private String id;

    private String filePath;

    @Column(unique = true, nullable = false)
    private String hash;

    @Column(nullable = false, length = 1024)
    private String caption;

    @Column(nullable = false)
    private byte[] embedding;


    private Instant createdAt = Instant.now();

    // ===== GETTERS =====

    public String getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHash() {
        return hash;
    }

    public String getCaption() {
        return caption;
    }

    public byte[] getEmbedding() {
        return embedding;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // ===== SETTERS =====

    public void setId(String id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setEmbedding(byte[] embedding) {
        this.embedding = embedding;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
