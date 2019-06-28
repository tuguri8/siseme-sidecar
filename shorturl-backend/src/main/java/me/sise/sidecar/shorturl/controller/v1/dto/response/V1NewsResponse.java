package me.sise.sidecar.shorturl.controller.v1.dto.response;

import lombok.Data;
import me.sise.sidecar.shorturl.service.model.NewsModel;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

import java.util.List;

@Data
public class V1NewsResponse {
    private String keyword;
    private List<NewsModel> result;

    public V1NewsResponse(String keyword, List<NewsModel> newsModel) {
        this.keyword = keyword;
        this.result= newsModel;
    }
}
