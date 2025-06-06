package com.example.subverge.exception.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "You are already subscribed to this service")
public class AlreadySubscribedException extends RuntimeException {

	public AlreadySubscribedException(String message) {
		super(format(message));
	}
}
