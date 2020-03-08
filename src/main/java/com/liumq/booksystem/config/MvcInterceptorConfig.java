package com.liumq.booksystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class MvcInterceptorConfig  extends WebMvcConfigurationSupport {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("==========MyInterceptor===========");
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**","/jq/**","/layui-v2.5.6/**","/Vue/**");

        super.addInterceptors(registry);
    }
}
