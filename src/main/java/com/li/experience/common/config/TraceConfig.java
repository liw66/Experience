package com.li.experience.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-29 13:44
 **/
@Configuration
public class TraceConfig {

    @Bean
    public CustomHttpTrace customHttpTrace(){
        return new CustomHttpTrace();
    }
}
