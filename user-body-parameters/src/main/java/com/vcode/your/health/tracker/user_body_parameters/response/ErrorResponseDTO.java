package com.vcode.your.health.tracker.user_body_parameters.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
	private String path;
	private String errorMessage;
	private HttpStatus httpStatus;
	private LocalDateTime localDateTime;
}
