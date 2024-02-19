package com.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
	private String msg;
	private LocalDateTime timestamp;

	public ApiResponse(String msg) {
		this.msg = msg;

		timestamp = LocalDateTime.now();
	}

}
