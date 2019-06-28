package me.sise.sidecar.shorturl.service;

import me.sise.sidecar.shorturl.service.model.NaverNewsResponse;
import me.sise.sidecar.shorturl.service.model.NewsModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private final static String CLIENT_ID = "8CulVgJRQkKb5Ir0bnTO";
    private final static String CLIENT_SECRET = "hi0KCmjhYD";
    private final ShortUrlService shortUrlService;

    public NewsServiceImpl(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @Override
    public List<NewsModel> getNews(String keyword) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);


        HttpEntity entity = new HttpEntity("parameters", headers);
        ResponseEntity<NaverNewsResponse> response= restTemplate.exchange("https://openapi.naver.com/v1/search/news.json?query={keyword}", HttpMethod.GET, entity, NaverNewsResponse.class, keyword);

        return response.getBody().getItems().stream().map(x -> transform(x)).collect(Collectors.toList());
    }

    private NewsModel transform(NaverNewsResponse.Item item) {
        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(item.getTitle());
        newsModel.setContent(item.getDescription());
        newsModel.setPubDate(item.getPubDate());
        newsModel.setShortUrl(shortUrlService.createShortUrl("", item.getLink()).getPath());
        return newsModel;
    }
}
