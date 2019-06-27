package me.sise.sidecar.shorturl.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.sise.sidecar.shorturl.controller.v1.dto.request.V1NaverNewsRequest;
import me.sise.sidecar.shorturl.controller.v1.dto.response.V1NaverNewsResponse;
import me.sise.sidecar.shorturl.service.NaverNewsService;
import me.sise.sidecar.shorturl.service.model.NaverNewsModel;

@RestController
@RequestMapping("/api/v1/news")
public class V1NaverNewsController {

	private final NaverNewsService naverNewsService;

	public V1NaverNewsController(NaverNewsService naverNewsService) {
		this.naverNewsService = naverNewsService;
	}

	@PostMapping
	public V1NaverNewsResponse createNaverNews(@RequestBody V1NaverNewsRequest request) {
		return new V1NaverNewsResponse(naverNewsService.getNaverNews(request));
	}
}
