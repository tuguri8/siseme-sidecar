package me.sise.sidecar.shorturl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.java.Log;
import me.sise.sidecar.shorturl.controller.v1.dto.request.V1ShortUrlRequest;
import me.sise.sidecar.shorturl.service.ShortUrlService;
import me.sise.sidecar.shorturl.service.model.ShortUrlModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class ApiApplicationTests {

	@Autowired
	private ShortUrlService service;

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mock; 

	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void contextLoads() {
	}

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
		log.info("getAllShortUrl Test......................");
		service.getAllShortUrl().forEach(shortUrlModel -> System.out.println(shortUrlModel));
	}

	@Test
	public void deleteAll() {
		service.deleteAllShortUrl();
	}

	@Test
	public void deleteByPath() {
		String path = "hbbjnfiQ";
		int delCnt = service.deleteShortUrl(path);
		System.out.println("Delete Count : " + delCnt);
	}

	@Test
	public void shortUrlController() throws Exception {

		V1ShortUrlRequest v1ShortUrlRequest = new V1ShortUrlRequest();
		v1ShortUrlRequest.setPath("");
		v1ShortUrlRequest.setWebUrl("www.google.com");
		v1ShortUrlRequest.setCustomPath("");

		String jsonContent = "{\"path\":\"\", \"customPath\":\"\", \"webUrl\":\"www.google.com\"}";

		log.info("shortUrlController TEST.....................");
		log.info(mock
				.perform(MockMvcRequestBuilders.post("/api/v1/urls").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(jsonContent))
				.andExpect(status().is(200)).andReturn().getResponse().getContentAsString());
	}
}
