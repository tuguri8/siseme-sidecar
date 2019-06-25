package me.sise.sidecar.shorturl.service.model;

import lombok.Data;

@Data
public class ShortUrlModel {
    private String path;
    private String webUrl;
    private String createdDate;
}
