package com.laodai.community.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration//普通的配置类
public class AlphaConfig {

    @Bean//定义第三方的bean 这个方法返回的对象将会装配到bean里
    public SimpleDateFormat simpleDateFormat(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }
}
