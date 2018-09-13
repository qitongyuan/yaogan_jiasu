package com.qty.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by qty.
 * 过滤器（过滤掉所有你想要拦截的请求）
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**")     
                .excludePathPatterns("/user/doLogin")
                .excludePathPatterns("/user/main")
                .excludePathPatterns("/user/show")
                .excludePathPatterns("/user/showEach")
                .excludePathPatterns("/user/history")
                .excludePathPatterns("/user/login");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	 registry.addResourceHandler("/Local/**").addResourceLocations("file:/E:/sharedPic/");
    	super.addResourceHandlers(registry);
    }
}
