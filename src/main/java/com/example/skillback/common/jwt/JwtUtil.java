package com.example.skillback.common.jwt;

import com.example.skillback.common.enums.RollEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String ACCESS_HEADER = "ACCESS";
    //    public static final String REFRESH_HEADER = "REFRESH";

    public static final String AUTHORIZATION_KEY = "auth";

    public static final String ACCESS_BEARER_PREFIX = "BEARER ";
    public static final Long ACCESS_TIME = 60 * 60 * 1000L;
    public static final Long REFRESH_TIME = 60 * 60 * 60 * 1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
        private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(ACCESS_BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String createAccessToken(String userIdentifier, RollEnum rollEnum) {
        Date date = new Date();
        return ACCESS_BEARER_PREFIX +
            Jwts.builder()
                .setSubject(userIdentifier)
                .claim(AUTHORIZATION_KEY, rollEnum)
                .setExpiration(new Date(date.getTime() + ACCESS_TIME))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invaild JWT Signature : 유효하지 않은 JWT 서명입니다 ");
        } catch (ExpiredJwtException e) {
            log.info("Expired JwtToken : 기간이 만료된 토근입니다");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported Token : 지원되지않은 토큰입니다");
        } catch (IllegalArgumentException e){
            log.info("IllegalArgument : 잘못된 형식의 토큰입니다");
        }
        return false;
    }

    public Claims getUserInformation(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }



}
