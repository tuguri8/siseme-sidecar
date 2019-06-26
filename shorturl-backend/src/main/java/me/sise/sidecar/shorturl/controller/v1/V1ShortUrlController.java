package me.sise.sidecar.shorturl.controller.v1;

import me.sise.sidecar.shorturl.controller.v1.dto.request.V1ShortUrlRequest;
import me.sise.sidecar.shorturl.controller.v1.dto.response.V1ShortUrlResponse;
import me.sise.sidecar.shorturl.service.ShortUrlService;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping()
    public List<ShortUrlModel> showAllShortUrl() {
        return shortUrlService.showAllShortUrl();
    }

    @GetMapping("/{path}")
    public V1ShortUrlResponse showShortUrl(@PathVariable String path) {
        return new V1ShortUrlResponse(shortUrlService.showShortUrl(path));
    }


    @PutMapping
    public V1ShortUrlResponse updateShortUrl(@RequestBody V1ShortUrlRequest request) {
        return new V1ShortUrlResponse(shortUrlService.updateShortUrl(request.getPath(), request.getCustomPath(), request.getWebUrl()));
    }

    @DeleteMapping
    public V1ShortUrlResponse deleteShortUrl(@RequestBody V1ShortUrlRequest request) {
        return new V1ShortUrlResponse(shortUrlService.deleteShortUrl(request.getPath()));
    }

}
