package com.example.client;

import com.example.client.config.RestTemplateConfig;
import com.example.client.dto.BeerGetDto;
import com.example.client.dto.BeerPostDto;
import com.example.client.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerRestService {
    private final RestTemplateConfig restTemplateConfig;

    // getForObject -> 다른것은 필요 없고, 응답 Body 만 있으면 될때
    public void getBeerObject() {
        // RestTemplate: Spring 에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

//        // RestTemplate 으로 GET 요청
//        String responseBody = restTemplate.getForObject(url, String.class);
//        log.info(responseBody);

        // @RequestBody, @ResponseBody => JSON -> DTO
        BeerGetDto responseBody = restTemplate.getForObject(url, BeerGetDto.class);
        log.info(responseBody.toString());
    }


    // STATUS LINE
    // RESPONSE HEADER
    // RESPONSE BODY
    // HTTP 응답 전체 확인
    public void getBeerEntity() {
        // RestTemplate: Spring 에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // RestController
        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);
        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());
    }

    // postForObject
    public void postBeerObject() {
        // RestTemplate: Spring 에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();

//        // post 요청을 보낼 때 requestBody 를 같이 전달
//        String responseBody = restTemplate.postForObject(
//                url,    // 요청 URL
//                dto,    // request Body
//                String.class    // 응답 해석 타입
//        );
//        log.info(responseBody);

        // String -> MessageDto 변경
        MessageDto response = restTemplate.postForObject(url, dto, MessageDto.class);
        log.info(response.toString());

        // 응답 Body 없이 응답하는 URL
        url = "http://localhost:8081/give-me-beer-204";
        ResponseEntity response1 = restTemplate.postForEntity(
                url,
                dto,
                Void.class  // null 의 클래스 (객체화 불가)
        );
        log.info(response1.getStatusCode().toString());
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(dto),
                Void.class
        );
    }

}
