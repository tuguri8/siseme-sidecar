package me.sise.sidecar.shorturl.service;

import org.springframework.stereotype.Service;

import me.sise.sidecar.shorturl.controller.v1.dto.request.V1NaverNewsRequest;
import me.sise.sidecar.shorturl.service.model.NaverNewsModel;

@Service
public interface NaverNewsService {
	
	NaverNewsModel getNaverNews(V1NaverNewsRequest request);
	
}
