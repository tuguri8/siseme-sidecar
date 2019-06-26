package me.sise.sidecar.shorturl.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import me.sise.sidecar.shorturl.common.utils.ShortUrlBuilder;
import me.sise.sidecar.shorturl.controller.v1.dto.request.V1ShortUrlRequest;
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

	public ShortUrlServiceImpl(ShortUrlBuilder shortUrlBuilder, ShortUrlRepository shortUrlRepository) {
		this.shortUrlBuilder = shortUrlBuilder;
		this.shortUrlRepository = shortUrlRepository;
	}

	// 1. 입력받은 path == null && 생성한 shortPath가 DB에 존재 -> PathIsAlreadyInUseException
	// 2. 입력받은 path != null && path가 DB에 존재 -> CustomPathIsAlreadyInUseException
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
		ShortUrl shortUrl = shortUrlRepository.findByPathAndIsActiveNot(path, Boolean.FALSE);
		return Objects.isNull(shortUrl) ? MAIN_URL : shortUrl.getWebUrl();
	}

	private Boolean existsPath(String path) {
		ShortUrl shortUrl = shortUrlRepository.findByPathAndIsActiveNot(path, Boolean.FALSE);
		return Objects.isNull(shortUrl) ? Boolean.FALSE : shortUrl.getIsActive();
	}

	private ShortUrlModel transform(ShortUrl shortUrl) {
		ShortUrlModel shortUrlModel = new ShortUrlModel();
		shortUrlModel.setPath(shortUrl.getPath());
		shortUrlModel.setWebUrl(shortUrl.getWebUrl());
		shortUrlModel.setCreatedDate(shortUrl.getCreatedDateTime().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
		return shortUrlModel;
	}

//	단축 URL 수정하기
//	---------------------------------
//	* 단축 URL 에 등록된 원본 URL 주소를 수정한다
//	* 정보 수정은 원본 PATH 를 통해서 라우트 URL / PATH 를 수정할 수 있다
//	CustomPath로 대체한다. PATH / CustomPath가 없으면 예외 발생
	@Override
	public ShortUrlModel updateShortUrl(V1ShortUrlRequest request) {

		String path = request.getPath();
		String customPath = request.getCustomPath();
		String webUrl = request.getWebUrl();

		if (StringUtils.isEmpty(path))
			throw new UrlShorteningFailureException.PathNullPointerException();

		// customPath, webUrl 모두 null이면 예외발생 (수정사항이 없음)
		if (StringUtils.isEmpty(customPath) && StringUtils.isEmpty(webUrl))
			throw new UrlShorteningFailureException.CustomPathAndWebUrlNullPointerException();

		if (existsPath(customPath))
			throw new UrlShorteningFailureException.CustomPathIsAlreadyInUseException();

		if (!existsPath(path))
			throw new UrlShorteningFailureException.PathNotFoundException();

		ShortUrl shortUrl = shortUrlRepository.findByPathAndIsActiveNot(path, Boolean.FALSE);

		if (!StringUtils.isEmpty(customPath))
			shortUrl.setPath(customPath);

		if (!StringUtils.isEmpty(webUrl))
			shortUrl.setWebUrl(webUrl);

		return transform(shortUrlRepository.save(shortUrl));
	}

	// 단축 URL 조회하기
	// ---------------------------------
	// * 단축 URL 에 등록된 원본 URL 주소를 PATH 를 통해 조회한다
	@Override
	public ShortUrlModel getShortUrl(String path) {

		if (StringUtils.isEmpty(path))
			throw new UrlShorteningFailureException.PathNullPointerException();

		// 검색하고자하는 Path가 DB에 존재하지 않는다고 해서 예외를 발생시켜야 하는가..?
		// DB에는 있지만 isActive가 false 경우에는???
		if (!existsPath(path))
			throw new UrlShorteningFailureException.PathNotFoundException();

		return transform(shortUrlRepository.findByPathAndIsActiveNot(path, Boolean.FALSE));
	}

	// ShortUrl 전체검색
	@Override
	public List<ShortUrlModel> getAllShortUrl() {
		return shortUrlRepository.findByIdGreaterThanAndIsActiveNot(0L, Boolean.FALSE).stream().map(this::transform)
				.collect(Collectors.toList());
	}

	// 단축 URL 삭제하기
	// ---------------------------------
	// * 단축 URL 주소를 삭제한다. isActive = false
	@Override
	public ShortUrlModel deleteShortUrl(String path) {

		if (StringUtils.isEmpty(path))
			throw new UrlShorteningFailureException.PathNullPointerException();

		if (!existsPath(path))
			throw new UrlShorteningFailureException.PathNotFoundException();

		ShortUrl shortUrl = shortUrlRepository.findByPathAndIsActiveNot(path, Boolean.FALSE);
		shortUrl.setIsActive(Boolean.FALSE);

		return transform(shortUrlRepository.save(shortUrl));
	}

}
