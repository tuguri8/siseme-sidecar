package me.sise.sidecar.shorturl.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import me.sise.sidecar.shorturl.common.utils.ShortUrlBuilder;
import me.sise.sidecar.shorturl.repository.entity.ShortUrl;
import me.sise.sidecar.shorturl.repository.entity.ShortUrlRepository;
import me.sise.sidecar.shorturl.service.exception.UrlShorteningFailureException;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    private final static Integer MAX_LENGTH = 8;
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String MAIN_URL = "https://www.naver.com";
    private final ShortUrlBuilder shortUrlBuilder;
    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlServiceImpl(ShortUrlBuilder shortUrlBuilder,
                               ShortUrlRepository shortUrlRepository) {
        this.shortUrlBuilder = shortUrlBuilder;
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrlModel createShortUrl(String path, String webUrl) {
        String shortPath = StringUtils.isEmpty(path) ? shortUrlBuilder.shorten(MAX_LENGTH) : path;
        if (existsPath(shortPath)) {
            if (StringUtils.isEmpty(path)) {
                throw new UrlShorteningFailureException.PathIsAlreadyInUseException();
            }
            throw new UrlShorteningFailureException.CustomPathIsAlreadyInUseException();
        }
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setPath(shortPath);
        shortUrl.setWebUrl(webUrl);
        shortUrl.setIsActive(Boolean.TRUE);
        return transform(shortUrlRepository.save(shortUrl));
    }

    @Override
    public String getRouteUrl(String path) {
        ShortUrl shortUrl = shortUrlRepository.findByPath(path);
        return Objects.isNull(shortUrl) ? MAIN_URL : shortUrl.getWebUrl();
    }

    private Boolean existsPath(String path) {
        ShortUrl shortUrl = shortUrlRepository.findByPath(path);
        return Objects.isNull(shortUrl) ? Boolean.FALSE : shortUrl.getIsActive();
    }

    private ShortUrlModel transform(ShortUrl shortUrl) {
        ShortUrlModel shortUrlModel = new ShortUrlModel();
        shortUrlModel.setPath(shortUrl.getPath());
        shortUrlModel.setWebUrl(shortUrl.getWebUrl());
        shortUrlModel.setCreatedDate(shortUrl.getCreatedDateTime()
                                             .format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        return shortUrlModel;
    }
    
    @Override
	public List<ShortUrlModel> getAllShortUrl() {

		List<ShortUrlModel> result = new ArrayList<ShortUrlModel>();
		List<ShortUrl> list = shortUrlRepository.findAll();
		list.forEach(shortUrl -> {
			ShortUrlModel shortUrlModel = transform(shortUrl);
			result.add(shortUrlModel);
		});
		return result;
	}

	@Override
	public int deleteShortUrl(String path) {
		return shortUrlRepository.deleteByPath(path);
	}

	@Override
	public void deleteAllShortUrl() {
		shortUrlRepository.deleteAll();
	}
}
