package com.example.producer.dto;

import lombok.Data;
// 사용자 <-> producer
@Data
// 사용자가 인코딩 요청을 보내는 DTO
public class JobRequest {
    private String filename;
}
