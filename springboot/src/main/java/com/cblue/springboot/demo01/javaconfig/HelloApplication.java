package com.cblue.springboot.demo01.javaconfig;

import java.nio.charset.Charset;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
@Configuration
public class HelloApplication {

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
    
  

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    
    
    
}