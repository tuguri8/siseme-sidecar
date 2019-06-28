package me.sise.sidecar.shorturl.service.model;

import lombok.Data;

import java.util.List;

@Data
public class NaverNewsResponse {
    private String lastBuildDate;
    private Long total;
    private Integer start;
    private Integer display;
    private List<Item> items;

    @Data
    public static class Item {
        private String title;
        private String originallink;
        private String link;
        private String description;
        private String PubDate;
    }
}
