package me.sise.sidecar.shorturl.service;

import me.sise.sidecar.shorturl.repository.entity.RouteLog;
import me.sise.sidecar.shorturl.repository.entity.RouteLogRepository;
import me.sise.sidecar.shorturl.repository.entity.ShortUrl;
import me.sise.sidecar.shorturl.repository.entity.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RouteLogServiceImpl implements RouteLogService {
    private final ShortUrlRepository shortUrlRepository;
    private final RouteLogRepository routeLogRepository;

    public RouteLogServiceImpl(ShortUrlRepository shortUrlRepository,
                               RouteLogRepository routeLogRepository) {
        this.shortUrlRepository = shortUrlRepository;
        this.routeLogRepository = routeLogRepository;
    }

    @Override
    public void logging(String path, String referer, String userAgent, String remoteAddress) {
        ShortUrl shortUrl = shortUrlRepository.findByPath(path);
        if (Objects.isNull(shortUrl)) {
            return;
        }
        RouteLog routeLog = new RouteLog();
        routeLog.setShortUrl(shortUrl);
        routeLog.setReferer(referer);
        routeLog.setUserAgent(userAgent);
        routeLog.setRemoteAddress(remoteAddress);
    }
}
