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
        //取消拦截静态文件，否则样式加载不出来
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**","/jq/**","/layui-v2.5.6/**","/Vue/**","/image/**");

        super.addInterceptors(registry);
    }
}
