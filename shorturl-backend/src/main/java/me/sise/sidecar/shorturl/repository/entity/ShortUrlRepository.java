package me.sise.sidecar.shorturl.repository.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long>{

	ShortUrl findByPathAndIsActiveNot(String path, boolean isActive);

	List<ShortUrl> findByIdGreaterThanAndIsActiveNot(Long id, boolean isActive);
}
