package com.softvan.Filter;

import com.softvan.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {
    private  final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Read token from Authentication header
        try {

            String token = request.getHeader("Authorization");
            if (token != null) {
                //do validation token

                String accessToken = token.split(" ")[1];
                String username = jwtUtil.getUsername(accessToken);

                //username should not be empty and security context should be   null
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    boolean isValid = jwtUtil.validateToken(accessToken, userDetails.getUsername());
                    if (isValid) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
                        //final object store in security context with userDetails(username,password)
                        // Means authentication success
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }

            }
            filterChain.doFilter(request, response);
        }
        catch(ExpiredJwtException ex){
            log.error("Expired JWT token.", ex);
            this.handlerExceptionResolver.resolveException(request,response,null,ex);
        }

        catch(JwtException ex){
            log.info("Delegating to global exception handler");
            this.handlerExceptionResolver.resolveException(request,response,null,ex);
        }
    }
}
