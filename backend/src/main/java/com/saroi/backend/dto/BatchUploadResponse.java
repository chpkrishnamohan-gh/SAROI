package com.saroi.backend.dto;

import java.util.List;

public class BatchUploadResponse {
    public int total;
    public int stored;
    public int duplicates;
    public List<String> storedIds;
}
