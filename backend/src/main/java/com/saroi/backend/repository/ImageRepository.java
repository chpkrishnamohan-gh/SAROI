package com.saroi.backend.repository;

import com.saroi.backend.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
    Optional<ImageEntity> findByHash(String hash);

    Optional<ImageEntity> findById(String id);
}
