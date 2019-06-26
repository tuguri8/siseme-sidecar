package me.sise.sidecar.shorturl.service;

import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

import java.util.List;

public interface ShortUrlService {
    ShortUrlModel createShortUrl(String path, String webUrl);

    String getRouteUrl(String path);

    ShortUrlModel updateShortUrl(String path, String customPath, String webUrl);

    ShortUrlModel deleteShortUrl(String path);

    ShortUrlModel showShortUrl(String path);

    List<ShortUrlModel> showAllShortUrl();
}
