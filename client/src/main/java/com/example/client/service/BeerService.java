package com.example.client.service;

import com.example.client.client.BeerClient;
import com.example.client.client.BeerWebClient;
import com.example.client.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeerService  {

//    private final BeerRestClient client;
//
//    public BeerService(BeerRestClient client) {
//        this.client = client;
//    }

    private final BeerClient client;

    public BeerService(BeerClient client) {
        this.client = client;
    }

    public void drinkBeer() {
        log.info("order beer");
        // 맥주 정보를 받아오는 방법은 비지니스 로직에서 벗어 날수 있지 않을까?
        BeerGetDto result = client.getBeer();
        log.info("{}는 맛있다.", result.getName());
    }
}
