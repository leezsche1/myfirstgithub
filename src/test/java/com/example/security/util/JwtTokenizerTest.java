package com.example.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class JwtTokenizerTest {


    @Value("${jwt.secretKey}")
    String accessSecret;
    public final Long ACCESS__TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L;

    @Test
    public void createToken() throws Exception {
        String email = "urstory@gmail.com";
        List<String> roles = List.of("ROLE_USER");
        Long id = 1L;
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);
        claims.put("userId", id);

        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);

        String JwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + ACCESS__TOKEN_EXPIRE_COUNT))
                .signWith(Keys.hmacShaKeyFor(accessSecret))
                .compact();

        System.out.println(JwtToken);

        Claims claims1 = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(accessSecret))
                .build()
                .parseClaimsJws(JwtToken)
                .getBody();

        Assertions.assertThat(claims1.getSubject()).isEqualTo(claims.getSubject());


    }

    @Test
    public void parseToken() throws Exception{


        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);
    }





}
