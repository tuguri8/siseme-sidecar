package me.sise.sidecar.shorturl.controller.v1.dto.request;

import lombok.Data;

@Data
public class V1NaverNewsRequest {

	private String query;
	private Integer display;
	private Integer start;
	private String sort;
}
