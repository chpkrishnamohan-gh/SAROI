package com.saroi.backend.service;

import com.saroi.backend.config.StorageConfig;
import com.saroi.backend.dto.BatchUploadResponse;
import com.saroi.backend.entity.ImageEntity;
import com.saroi.backend.repository.ImageRepository;
import com.saroi.backend.util.EmbeddingUtil;
import com.saroi.backend.util.HashUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ImageIngestService {

    private final ImageRepository repo;
    private final MlClientService ml;

    public ImageIngestService(ImageRepository repo, MlClientService ml) {
        this.repo = repo;
        this.ml = ml;
    }

    public BatchUploadResponse ingest(MultipartFile[] files) throws Exception {

        int stored = 0, dup = 0;
        var ids = new ArrayList<String>();

        for (MultipartFile f : files) {
            byte[] bytes = f.getBytes();
            String hash = HashUtil.sha256(bytes);

            if (repo.findByHash(hash).isPresent()) {
                dup++;
                continue;
            }

            var mlResp = ml.analyze(f.getResource());
            var path = StorageConfig.IMAGE_ROOT.resolve(hash + ".img");

            Files.write(path, bytes);

            ImageEntity e = new ImageEntity();
            e.setId(UUID.randomUUID().toString());
            e.setHash(hash);
            e.setFilePath(path.toString());
            e.setCaption(mlResp.getCaption());
            e.setEmbedding(EmbeddingUtil.toBytes(mlResp.getEmbedding()));

            repo.save(e);
            stored++;
            ids.add(e.getId());
        }

        BatchUploadResponse r = new BatchUploadResponse();
        r.total = files.length;
        r.stored = stored;
        r.duplicates = dup;
        r.storedIds = ids;
        return r;
    }
    public ImageRepository getRepository() {
        return repo;
    }

}
