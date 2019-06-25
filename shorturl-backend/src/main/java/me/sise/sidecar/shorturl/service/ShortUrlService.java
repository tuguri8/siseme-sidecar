package me.sise.sidecar.shorturl.service;

import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

import java.util.List;

public interface ShortUrlService {
	ShortUrlModel createShortUrl(String path, String webUrl);

	String getRouteUrl(String path);

	List<ShortUrlModel> getAllShortUrl();

	int deleteShortUrl(String path);
	
	void deleteAllShortUrl();
}
