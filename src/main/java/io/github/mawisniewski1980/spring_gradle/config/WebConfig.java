package io.github.mawisniewski1980.spring_gradle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**", "/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "/webjars/", "classpath:/resources/", "classpath:/static/");
    }
}
