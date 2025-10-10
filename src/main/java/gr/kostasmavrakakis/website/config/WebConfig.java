package gr.kostasmavrakakis.website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                    "/img/**",
                    "/favicon.ico", 
                    "/favicon-16x16.png", 
                    "/favicon-32x32.png", 
                    "/apple-touch-icon.png"
                )
                .addResourceLocations(
                    "classpath:/static/img/",
                    "classpath:/static/"
                    )
                .setCachePeriod(31536000);

        registry.addResourceHandler(
                    "/css/**", 
                    "/js/**"
                )
                .addResourceLocations(
                    "classpath:/static/css/", 
                    "classpath:/static/js/"
                )
                .setCachePeriod(604800);
    }
}
