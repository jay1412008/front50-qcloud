package com.netflix.spinnaker.front50.config;

import com.netflix.spinnaker.front50.model.QcloudStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnExpression("${spinnaker.qcloud.enabled:false}")
@EnableConfigurationProperties(QcloudStorageProperties.class)
public class QcloudStorageConfig extends CommonStorageServiceDAOConfig {
  @Bean
  @ConditionalOnMissingBean(RestTemplate.class)
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public QcloudStorageService qcloudStorageService(QcloudStorageProperties qcloudStorageProperties) {
    return new QcloudStorageService(qcloudStorageProperties);
  }
}
