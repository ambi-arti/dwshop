package com.artemius.dwshop.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
    
    public void addViewControllers(ViewControllerRegistry reg) {
	reg.addViewController("/casual").setViewName("casual");
	reg.addViewController("/hiddenShit").setViewName("hiddenShit");
	reg.addViewController("/login").setViewName("login");
    }
    
 /*   public void addResourceHandlers(ResourceHandlerRegistry registry) {
    
        // Style resources
        registry.addResourceHandler("/styles/**") //
                  .addResourceLocations("/WEB-INF/resources/static/styles/").setCachePeriod(31556926);
        // Script resources
        registry.addResourceHandler("/scripts/**") //
                  .addResourceLocations("/WEB-INF/resources/static/scripts/").setCachePeriod(31556926);
        
    }*/
}
