package me.sise.sidecar.shorturl.controller.v1;

import me.sise.sidecar.shorturl.controller.v1.dto.request.V1ShortUrlRequest;
import me.sise.sidecar.shorturl.controller.v1.dto.response.V1ShortUrlResponse;
import me.sise.sidecar.shorturl.service.ShortUrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/urls")
public class V1ShortUrlController {
    private final ShortUrlService shortUrlService;

    public V1ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public V1ShortUrlResponse createShortUrl(@RequestBody V1ShortUrlRequest request) {
        return new V1ShortUrlResponse(shortUrlService.createShortUrl(request.getPath(), request.getWebUrl()));
    }
}
