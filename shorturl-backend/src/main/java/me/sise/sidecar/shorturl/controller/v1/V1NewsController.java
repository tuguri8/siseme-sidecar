package me.sise.sidecar.shorturl.controller.v1;

import me.sise.sidecar.shorturl.controller.v1.dto.response.V1NewsResponse;
import me.sise.sidecar.shorturl.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news")
public class V1NewsController {
    private final NewsService newsService;

    public V1NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{keyword}")
    public V1NewsResponse getNews(@PathVariable String keyword) {
        return new V1NewsResponse(keyword, newsService.getNews(keyword));
    }
}
