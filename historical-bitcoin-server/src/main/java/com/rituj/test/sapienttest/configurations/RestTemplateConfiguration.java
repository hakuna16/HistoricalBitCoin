package com.rituj.test.sapienttest.configurations;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/** Configuration class for making {@link RestTemplate} as injectable. */
@Configuration
public class RestTemplateConfiguration {

  /** Rest template connection time out. */
  @Value(value = "${rest-template.connection.timeout}")
  private Integer connectionTimeOut;

  /** Rest template read time out. */
  @Value(value = "${rest-template.read.timeout}")
  private Integer readTimeOut;

  @Bean
  public RestTemplate restTemplate(final RestTemplateBuilder builder) {
    return builder
        .setConnectTimeout(Duration.ofMillis(connectionTimeOut))
        .setReadTimeout(Duration.ofMillis(readTimeOut))
        .build();
  }
}
