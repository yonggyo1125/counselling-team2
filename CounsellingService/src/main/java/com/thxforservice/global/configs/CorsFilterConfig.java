package com.thxforservice.global.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("!test") // 테스트에선 사용 X
public class CorsFilterConfig {


    @Value("${cors.allow.origins}")
    private String allowedOrigins;

    // Cors 관련 헤더 - 응답 헤더 추가
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*"); // 모든 요청 메서드 허용
        config.addAllowedHeader("*"); // 모든 요청 헤더 허용
        if (StringUtils.hasText(allowedOrigins)) {
            List<String> origins = Arrays.stream(allowedOrigins.split(",")).toList();
            config.setAllowedOrigins(origins);
            config.setAllowCredentials(true);
        } else {
            config.addAllowedOrigin("*");
        }
        config.addExposedHeader("*");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
