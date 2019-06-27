package me.sise.sidecar.shorturl.service.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NaverNewsModel {

	private Date lastBuildDate;
	private Integer total;
	private Integer start;
	private Integer display;
	private List<NaverNewsItems> items;
}
