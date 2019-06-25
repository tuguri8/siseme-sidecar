package me.sise.sidecar.shorturl.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteLogRepository extends JpaRepository<RouteLog, Long> {
}
