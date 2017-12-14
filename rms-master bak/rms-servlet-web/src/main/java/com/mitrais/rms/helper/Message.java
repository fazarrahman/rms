package com.mitrais.rms.helper;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;

public class Message {
	private String message;
	private MESSAGE_TYPE messageType;

	public static enum MESSAGE_TYPE {
		error, information
	}

	public Message(MESSAGE_TYPE messageType, String message) {
		this.messageType = messageType;
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public MESSAGE_TYPE getMessageType() {
		return this.messageType;
	}

	public Cookie setToCookie(Cookie[] cookies) {
		Stream<Cookie> cookiesStream = Arrays.stream(cookies);
		Optional<Cookie> optCookie = cookiesStream.filter(c -> c.getName().equals("message")).findFirst();
		Cookie cookie;
		if (optCookie.isPresent()) {
			cookie = optCookie.get();
			cookie.setValue(this.message);
		} else {
			cookie = new Cookie("message", this.message);
		}
		cookie.setMaxAge(1);
		return cookie;

	}
}
