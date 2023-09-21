package com.example.auth.jwt;

import com.example.auth.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Component
// JWT 관련 기능(생성, 인증)들을 넣어두기 위한 기능성 클래스
public class JwtTokenUtils {
    // JWT 는 암호화를 거쳐서 만들어지는데,
    // 이를 위한 암호키가 필요하다.
    private final Key signingKey;
    private final JwtParser jwtParser;

    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String jwtSecret
    ) {
        // JWT 를 생성하기 위한 key 설정
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        // JWT 번역기 만들기
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.signingKey)
                .build();
    }

    // 1. JWT가 유효한지 판단하는 메소드
    //      jjwt 라이브러리에서는 JWT를 해석하는 과정에서
    //      유효하지 않으면 예외 발생
    public boolean validate(String token) {
        try {
            // 정당한 JWT면 true, 아니면 false
            // parseClaimsJws: 암호화된 JWT를 해석하기위한 메소드
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("invalid jwt: {}", e.getClass());
            return false;
        }
    }

    // JWT를 인자로 받고, 그 JWT를 해석해서
    // 사용자 정보를 회수하는 메소드
    public Claims parseClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

    // 주어진 사용자 정보를 바탕으로 JWT 를 문자열로 생성
    // 새로운 JWT를 발급하는 메소드
    public String generateToken(UserDetails userDetails) {

        // Claims: JWT에 담길 데이터의 키 (맵의 키와 비슷한 용도)
        // 이 부분은 JWT에 담고싶은 사용자 정보를 담는다.
        Claims jwtClaims = Jwts.claims()
                // 사용자 정보 등록
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)));
//        // 추가 정보를 담을수도 있다.
//        jwtClaims.put("eml",((CustomUserDetails)userDetails).getEmail());
        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signingKey)
                .compact();
    }


}
