package com.buchstadt.config;

import com.buchstadt.annotaion.GlobalUrl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private GlobalUrlProps pathProps;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(
                pathProps.getGlobalPrefix(),
                c -> c.isAnnotationPresent(GlobalUrl.class));
    }

}
