package me.sise.sidecar.shorturl.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
	ShortUrl findByPath(String path);

	@Modifying
	@Transactional
	@Query("DELETE FROM ShortUrl s WHERE s.path =:path")
	int deleteByPath(@Param("path") String path);
}
