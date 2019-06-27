package me.sise.sidecar.shorturl.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;
import me.sise.sidecar.shorturl.controller.v1.dto.request.V1ShortUrlRequest;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class ShortUrlServiceTest {

	@Autowired
	private ShortUrlService service;

	@Test
	public void createShortURL() {

		String[] webUrlArr = new String[] { "www.naver.com", "www.google.com", "www.daum.net" };

		for (int i = 0; i < webUrlArr.length; i++) {

			ShortUrlModel shortUrlModel = service.createShortUrl("", webUrlArr[i]);
			System.out.println(shortUrlModel.toString());
		}
	}

	@Test
	public void selectShortUrl() {

		String[] webUrlArr = new String[] { "www.naver.com", "www.google.com", "www.daum.net" };
		for (int i = 0; i < webUrlArr.length; i++) {

			ShortUrlModel shortUrlModel = service.createShortUrl("", webUrlArr[i]);
			String routeUrl = service.getRouteUrl(shortUrlModel.getPath());
			System.out.println(shortUrlModel.getPath() + " -> " + routeUrl);
		}
	}

	@Test
	public void getAllShortUrl() {
		service.getAllShortUrl().forEach(shortUrlModel -> System.out.println(shortUrlModel));
	}

	@Test
	public void getShortUrl() {
		String path = "gPDR7N8N";
		ShortUrlModel shortUrlModel = service.getShortUrl(path);
		log.info(shortUrlModel.toString());
	}

	@Test
	public void deleteShortUrl() {
		String[] pathArr = new String[] { "gPDR7N8N", "jaGYV7WD", "gvXFgyuT", "dS5S4myF", "ijCTlIp2" };
		for (int i = 0; i < pathArr.length; i++) {
			service.deleteShortUrl(pathArr[i]);
		}
	}

	@Test
	public void updateShortUrl() {
		V1ShortUrlRequest request = new V1ShortUrlRequest();
		request.setCustomPath("AAAAAAAA");
		request.setPath("chqBnIDB");
		request.setWebUrl("www.github.com");
		service.updateShortUrl(request);
	}

}
