package com.aydemir.mappyplaces.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("mappy-places.google-maps")
public class GoogleMapsConfiguration {
    private String apiKey;
}
