package me.sise.sidecar.shorturl.controller.v1.dto.request;

import lombok.Data;

@Data
public class V1ShortUrlRequest {
    private String path;
    private String customPath;
    private String webUrl;
}
