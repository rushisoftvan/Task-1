package com.softvan.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private static final String AUTHORIZATION = "Authorization";
    private static final String USER_ID = "userId";

    private static final String SYSTEM_ROLE = "role";
    public String resolveToken(HttpServletRequest request) {//throws AuthenticationException
        String bearerToken = request.getHeader(AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        if (bearerToken != null) {
            return bearerToken;
        }
        return bearerToken;
    }


    public boolean validateToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(jwtProperties.getJwtSecretKey()).parseClaimsJws(token);
        return true;
    }

    public Authentication getAuthentication(String token)  {
        log.info("<<<<<getAuthentication");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(getSystemRole(token)));
        UserDetails userDetails = new User(getUsername(token), getUsername(token), true, false, false, false, grantedAuthorities);
          log.info("getAuthentication>>>>>>>>>>>");
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getJwtSecretKey()).parseClaimsJws(token).getBody().getSubject();
    }
    public Integer getUserIdToken(String token) {
        return (Integer) Jwts.parser().setSigningKey(jwtProperties.getJwtSecretKey()).parseClaimsJws(token).getBody().get(USER_ID);
    }
    public String getSystemRole(String token) {
        return (String) Jwts.parser().setSigningKey(jwtProperties.getJwtSecretKey()).parseClaimsJws(token).getBody().get(SYSTEM_ROLE);
    }

    // create token for login user
    public String createToken(String username, Integer userId, String role) {
        log.info("<<<<<< craete token");
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(USER_ID, userId);
        claims.put(SYSTEM_ROLE, role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + MINUTES.toMillis(jwtProperties.getJwtExpireTimeInMinute()));
        log.info("create token>>>>>>");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getJwtSecretKey())
                .compact();
    }
}
