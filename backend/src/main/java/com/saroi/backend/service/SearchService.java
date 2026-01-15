package com.saroi.backend.service;

import com.saroi.backend.dto.SearchResultDto;
import com.saroi.backend.repository.ImageRepository;
import com.saroi.backend.util.EmbeddingUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SearchService {

    private final ImageRepository repo;

    public SearchService(ImageRepository repo) {
        this.repo = repo;
    }

    public List<SearchResultDto> search(float[] q, int offset, int limit) {
        return repo.findAll().stream()
                .map(e -> {
                    float[] v = EmbeddingUtil.fromBytes(e.getEmbedding());
                    double score = cosine(q, v);
                    SearchResultDto r = new SearchResultDto();
                    r.imageId = e.getId();
                    r.filePath = e.getFilePath();
                    r.caption = e.getCaption();
                    r.score = score;
                    return r;
                })
                .sorted(Comparator.comparingDouble(r -> -r.score))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    private double cosine(float[] a, float[] b) {
        double dot = 0, na = 0, nb = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            na += a[i] * a[i];
            nb += b[i] * b[i];
        }
        return dot / (Math.sqrt(na) * Math.sqrt(nb));
    }
}
