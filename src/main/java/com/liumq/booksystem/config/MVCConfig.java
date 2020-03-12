package com.liumq.booksystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MVCConfig  implements WebMvcConfigurer  {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("==================ViewController================");
        //配置指定请求直接跳转到对应页面
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/devsoft1/**").addResourceLocations("file:D:/devsoft1/");
      //  registry.addResourceHandler("/image/**").addResourceLocations(("file:E:/booksystem/src/main/resources/static/image/"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/tologin");
    }
}
