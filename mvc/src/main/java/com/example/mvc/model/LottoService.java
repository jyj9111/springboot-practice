package com.example.mvc.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class LottoService {
    private int hits = 0;
    private List<List<Integer>> history = new ArrayList<>();

    //누군가가 방문했을 때 호출하는 메소드
    public int addHit() {
        return ++hits;
    }

    // lotto 번호 추출 메소드
    public List<Integer> createLottoNum() {
        List<Integer> lottolist = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            lottolist.add(random.nextInt(1, 46)); // 임이의 정수를 범위내로 반환 1에서 45까지
        }

        history.add(lottolist);

        return lottolist;
    }

    // history 메소드

}
