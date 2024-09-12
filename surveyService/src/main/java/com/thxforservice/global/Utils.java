package com.thxforservice.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Utils { // 빈의 이름 - utils

    private final MessageSource messageSource;
    private final HttpServletRequest request;
    private final DiscoveryClient discoveryClient;

    public Map<String, List<String>> getErrorMessages(Errors errors) {
        // FieldErrors

        Map<String, List<String>> messages = errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, e -> getCodeMessages(e.getCodes()), (p1, p2) -> p1));

        // GlobalErrors
        List<String> gMessages = errors.getGlobalErrors()
                .stream()
                .flatMap(e -> getCodeMessages(e.getCodes()).stream()).toList();

        if (!gMessages.isEmpty()) {
            messages.put("global", gMessages);
        }
        return messages;
    }


    public List<String> getCodeMessages(String[] codes) {
        ResourceBundleMessageSource ms = (ResourceBundleMessageSource) messageSource;
        ms.setUseCodeAsDefaultMessage(false);

        List<String> messages = Arrays.stream(codes)
                .map(c -> {
                    try {
                        return ms.getMessage(c, null, request.getLocale());
                    } catch (Exception e) {
                        return "";
                    }
                })
                .filter(s -> !s.isBlank())
                .toList();

        ms.setUseCodeAsDefaultMessage(true);
        return messages;
    }

    public String getMessage(String code) {
        List<String> messages = getCodeMessages(new String[] {code});

        return messages.isEmpty() ? code : messages.get(0);
    }

    public String url(String url, String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

        try {
            return String.format("%s%s", instances.get(0).getUri().toString(), url);
        } catch (Exception e) {
            return String.format("%s://%s:%d%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath(), url);
        }
    }

//km?
    public String  surveyUrl(String url){
        // SurveyService의 인스턴스 목록을 가져옴
        List<ServiceInstance> instances = discoveryClient.getInstances("surveyService");

        if (instances != null && !instances.isEmpty()) {
            // 첫 번째 인스턴스의 URI를 사용해 URL을 생성
            return String.format("%s%s", instances.get(0).getUri().toString(), url);
        } else {
            throw new IllegalStateException("SurveyService 인스턴스를 찾을 수 없습니다.");
        }
    }

    /**
     * 요청 받은 JWT 토큰 조회
     *
     * @return
     */
    public String getToken() {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)
                && bearerToken.toUpperCase().startsWith("BEARER ")) {
            return bearerToken.substring(7).trim();
        }

        String token = request.getParameter("token");
        if (StringUtils.hasText(token)) {
            return token;
        }

        return null;
    }

    public int guestUid() {
        String ip = request.getRemoteAddr();
        String ua = request.getHeader("User-Agent");
        return Objects.hash(ip, ua);
    }

    /**
     * 접속 장비가 모바일인지 체크
     *
     * @return
     */
    public boolean isMobile() {

        // 모바일 수동 전환 체크, 처리
        HttpSession session = request.getSession();
        String device = (String) session.getAttribute("device");

        if (StringUtils.hasText(device)) {
            return device.equals("MOBILE");
        }

        // User-Agent 요청 헤더 정보
        String ua = request.getHeader("User-Agent");

        String pattern = ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*";

        return ua.matches(pattern);
    }


    /**
     * 모바일, PC 뷰 템플릿 경로 생성
     *
     * @param path
     * @return
     */
    public String tpl(String path) {
        String prefix = isMobile() ? "mobile/" : "front/";

        return prefix + path;
    }

}