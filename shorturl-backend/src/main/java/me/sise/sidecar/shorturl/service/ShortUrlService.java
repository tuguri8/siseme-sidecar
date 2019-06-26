package me.sise.sidecar.shorturl.service;

import java.util.List;

import me.sise.sidecar.shorturl.controller.v1.dto.request.V1ShortUrlRequest;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

public interface ShortUrlService {

	ShortUrlModel createShortUrl(String path, String webUrl);

	String getRouteUrl(String path);

	// 조회
	ShortUrlModel getShortUrl(String path);

	List<ShortUrlModel> getAllShortUrl();

	ShortUrlModel updateShortUrl(V1ShortUrlRequest request);
	
	ShortUrlModel deleteShortUrl(String path);

}
