package me.sise.sidecar.shorturl.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import me.sise.sidecar.shorturl.controller.v1.dto.request.V1NaverNewsRequest;
import me.sise.sidecar.shorturl.service.model.NaverNewsModel;

@Service
public class NaverNewsServiceImpl implements NaverNewsService {

	// 암호화 미구현
	private String ClientId = "hdcD8fXsfj5HNEYcSqVL";
	private String ClientSecret = "ZxcJpcmOlH";

	private static final String URL = "https://openapi.naver.com/v1/search/news";
	private static final String DISPLAY = "10";
	private static final String START = "1";
	private static final String SORT = "sim";

	@Override
	public NaverNewsModel getNaverNews(V1NaverNewsRequest request) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", ClientId);
		headers.set("X-Naver-Client-Secret", ClientSecret);

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(URL)
				.queryParam("query", request.getQuery())
				.queryParam("display", DISPLAY)
				.queryParam("start", START)
				.queryParam("sort", SORT)
				.build();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<NaverNewsModel> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				new HttpEntity<String>(headers), NaverNewsModel.class);
		return response.getBody();
	}

}
