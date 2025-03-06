package com.movieapp.booking.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConstants {

	@Value("${com.movieapp.booking.default.showtimes}")
	public List<String> DEFAULT_SHOW_TIME;

}
