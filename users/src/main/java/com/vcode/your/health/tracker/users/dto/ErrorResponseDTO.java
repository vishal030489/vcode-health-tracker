package com.vcode.your.health.tracker.users.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class ErrorResponseDTO {
	private String apiPath;
	private HttpStatus status;
	private String errorMessage;
    private LocalDateTime errorTime;
}
