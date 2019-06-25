package me.sise.sidecar.shorturl.service;

public interface RouteLogService {
    void logging(String path, String referer, String userAgent, String remoteAddress);
}
