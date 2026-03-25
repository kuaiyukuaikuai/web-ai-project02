package com.aliyun.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AliyunOSS自动配置类
 */
@EnableConfigurationProperties(AliyunOSSProperties.class)//@EnableConfigurationProperties = "启用配置属性"
@Configuration//标记这个类是一个 Spring 配置类
public class AliyunOSSAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean//创建这个 Bean 的条件，如果容器中没有这个 Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties);
    }
}
