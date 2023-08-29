package com.emekaokechukwu.getstartedanime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class GetStartedAnimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetStartedAnimeApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*"); // Allow requests from any origin
		corsConfig.addAllowedMethod("*");
		corsConfig.addAllowedHeader("*");
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsFilter(source);
	}
}
