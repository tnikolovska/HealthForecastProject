package com.teodora.springcloud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private int statusCode;
	private String message;
	
	public ErrorResponse(int statusCode,String message) {
		super();
		this.statusCode=statusCode;
		this.message = message;
	}
}
