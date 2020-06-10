package com.artemius.dwshop;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer{
    public void addViewControllers(ViewControllerRegistry reg) {
	reg.addViewController("/").setViewName("home");
	reg.addViewController("/index").setViewName("index");
	//reg.addViewController("/hiddenShit").setViewName("hiddenShit");
    }
}
