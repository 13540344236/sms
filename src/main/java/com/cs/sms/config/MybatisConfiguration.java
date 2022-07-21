package com.cs.sms.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置类
 *
 */
@Slf4j
@Configuration
@MapperScan("com.cs.sms.mapper")
public class MybatisConfiguration {

    public MybatisConfiguration() {
        log.debug("加载配置类：MybatisConfiguration");
    }

}