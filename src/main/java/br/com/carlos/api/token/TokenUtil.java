package br.com.carlos.api.token;

import br.com.carlos.api.dto.LoginRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TokenUtil {

    private static final String PREFIX = "Bearer ";
    private static final long EXPIRATION = 12 * 60 * 60 * 1000;
    private static final String SECRET_KEY = "AKFJBDJDWOPLÇLOQWERTYUIOPNNFNFDFUIF";
    private static final String EMISSOR = "Carlos Henrique";


    public static String createToken(LoginRequestDto loginRequestDto) {
        Key secretKeykey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return PREFIX + Jwts.builder()
                .setSubject(loginRequestDto.getEmail())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKeykey, SignatureAlgorithm.HS256)
                .compact();
    }
}

