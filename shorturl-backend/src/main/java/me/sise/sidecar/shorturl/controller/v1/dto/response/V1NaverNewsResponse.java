package me.sise.sidecar.shorturl.controller.v1.dto.response;

import java.util.Date;
import java.util.List;

import lombok.Data;
import me.sise.sidecar.shorturl.service.model.NaverNewsItems;
import me.sise.sidecar.shorturl.service.model.NaverNewsModel;

@Data
public class V1NaverNewsResponse {

	private Date lastBuildDate;
	private Integer total;
	private Integer start;
	private Integer display;
	private List<NaverNewsItems> items;

	public V1NaverNewsResponse(NaverNewsModel naverNewsModel) {
		this.lastBuildDate = naverNewsModel.getLastBuildDate();
		this.total = naverNewsModel.getTotal();
		this.start = naverNewsModel.getStart();
		this.display = naverNewsModel.getDisplay();
		this.items = naverNewsModel.getItems();
	}

}
