package me.sise.sidecar.shorturl.service;

import me.sise.sidecar.shorturl.service.model.NewsModel;

import java.util.List;

public interface NewsService {
    List<NewsModel> getNews(String keyword);
}
