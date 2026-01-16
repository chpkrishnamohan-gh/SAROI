package com.saroi.backend.controller;

import com.saroi.backend.dto.BatchUploadResponse;
import com.saroi.backend.dto.SearchResultDto;
import com.saroi.backend.service.ImageIngestService;
import com.saroi.backend.service.MlClientService;
import com.saroi.backend.service.SearchService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;
import org.springframework.http.MediaType;
import java.nio.file.Path;


@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageIngestService ingest;
    private final MlClientService ml;
    private final SearchService search;

    public ImageController(ImageIngestService ingest,
                           MlClientService ml,
                           SearchService search) {
        this.ingest = ingest;
        this.ml = ml;
        this.search = search;
    }

    @PostMapping("/batch-upload")
    public BatchUploadResponse upload(@RequestParam("files") MultipartFile[] files)
            throws Exception {
        return ingest.ingest(files);
    }

    @GetMapping("/search")
    public List<SearchResultDto> search(
            @RequestParam String query,
            @RequestParam int offset,
            @RequestParam int limit
    ) {
        return search.search(ml.embedText(query), offset, limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) throws Exception {

        var image = ingest
                .getRepository()
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        Path path = Path.of(image.getFilePath());
        byte[] data = Files.readAllBytes(path);

        MediaType type = MediaType.APPLICATION_OCTET_STREAM;
        String file = image.getFilePath().toLowerCase();

        if (file.endsWith(".jpg") || file.endsWith(".jpeg")) {
            type = MediaType.IMAGE_JPEG;
        } else if (file.endsWith(".png")) {
            type = MediaType.IMAGE_PNG;
        }

        return ResponseEntity
                .ok()
                .contentType(type)
                .body(data);
    }

}
