package me.sise.sidecar.shorturl.service.model;

import lombok.Data;

@Data
public class NewsModel {
    private String title;
    private String content;
    private String shortUrl;
    private String pubDate;
}
