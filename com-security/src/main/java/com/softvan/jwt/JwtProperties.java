package com.softvan.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Slf4j
@Setter
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtProperties {


    public static final String DEFAULT_PERMIT_ALL_PATH = "/login";
    private long jwtExpireTimeInMinute= 5;

    private  String jwtSecretKey;

    private List<String> permitAllPaths=asList(DEFAULT_PERMIT_ALL_PATH);


    @PostConstruct
    public void inIt(){
        log.info("JwtProperties been is created");
    }

}
