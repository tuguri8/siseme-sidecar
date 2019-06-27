package me.sise.sidecar.shorturl.service.model;

import java.util.Date;

import lombok.Data;

@Data
public class NaverNewsItems {

	private String title;
	private String originallink;
	private String link;
	private String description;
	private Date pubDate;

}
