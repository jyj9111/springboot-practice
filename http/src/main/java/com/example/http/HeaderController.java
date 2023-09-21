package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HeaderController {
    // 한개의 헤더를 받을 때
    // `/header-one`으로 들어온 HTTP 요청에 대하여
    // 헤더 중 `x-likelion` 이라는 헤더의 값을 인자로 전달받고 싶을 때
    @PostMapping("header-one")
    public String getHeader(@RequestHeader("x-likelion") String header) {
        log.info("POST /header-one header: " + header);
        return "index";
    }

    // `/header-optional`으로 들어온 HTTP 요청에 대하여
    // 헤더 중 `x-likelion`이 있으면 할당, 없으면 null로 받고 싶을 때
    @PostMapping("header-optional")
    public String getHeaderOptional(
            @RequestHeader(value = "x-likelion", required = false) String header
    ) {
        log.info("POST /header-optional header: " + header);
        return "index";
    }

    // `header-type`으로 들어온 HTTP 요청에 대하여
    // 헤더 중 `x-likelion-int`가 있으면 Integer에 할당
    @PostMapping("/header-type")
    public String getHeaderInteger(
            @RequestHeader(value = "x-likelion-int") Integer header
    ) {
        log.info("POST /header-type header: " + header);
        return "index";
    }

    // `header-all`로 들어온 HTTP 요청의
    // 모든 헤더를 확인하고 싶을 때
    @PostMapping("/header-all")
    public String getHeaderAll(
//            방법 1
//            @RequestHeader // 모든 헤더를 다 가져올 때
//            Map<String, String> headerMap
            // 방법 2
//            @RequestHeader // 모든 헤더를 다 가져올 때
//            MultiValueMap<String, String> headerMap
            // 방법 3
            @RequestHeader
            HttpHeaders headerMap
    ) {
        log.info("POST /header-all");
//        방법 1
//        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
//            log.info(String.format("%s: %s", entry.getKey(), entry.getValue()));
//        }
        // 방법 2, 3
        for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
            log.info(String.format("%s: %s", entry.getKey(), entry.getValue()));
        }
        return "index";
    }
}
