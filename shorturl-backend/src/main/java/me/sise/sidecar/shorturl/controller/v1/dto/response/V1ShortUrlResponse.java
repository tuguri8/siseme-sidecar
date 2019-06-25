package me.sise.sidecar.shorturl.controller.v1.dto.response;

import lombok.Data;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

@Data
public class V1ShortUrlResponse {
    private String path;
    private String webUrl;
    private String createdDate;

    public V1ShortUrlResponse(ShortUrlModel shortUrlModel) {
        this.path = shortUrlModel.getPath();
        this.webUrl = shortUrlModel.getWebUrl();
        this.createdDate = shortUrlModel.getCreatedDate();
    }
}
