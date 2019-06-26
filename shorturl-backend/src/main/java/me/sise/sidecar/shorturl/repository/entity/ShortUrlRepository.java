package me.sise.sidecar.shorturl.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByPath(String path);
    ShortUrl findByPathAndIsActive(String path, boolean isActive);
    Optional<List<ShortUrl>> findAllByIsActive(boolean isActive);
}
