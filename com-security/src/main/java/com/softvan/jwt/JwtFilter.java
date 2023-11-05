package com.softvan.jwt;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Arrays.stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver exceptionResolver;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String INVALID_JWT_TOKEN = "Invalid JWT token";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request);
        log.info("<<<<<< JwtFilter");
        try {
            String token = jwtTokenProvider.resolveToken(request);
                if ((token != null && !token.isEmpty())) {
                    jwtTokenProvider.validateToken(token);
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    log.info("Authentication object :: {}" , authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            filterChain.doFilter(request, response);
            log.info("JwtFilter>>>>>>");
        } catch (JwtException ex) {
            exceptionResolver.resolveException(request,response, null , ex);
        }
    }
}
