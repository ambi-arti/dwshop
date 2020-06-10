package com.artemius.dwshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.artemius.dwshop.services.DepsService;

@SpringBootApplication
public class DwshopApplication {
    	@Autowired
    	public DepsService depsService;
	public static void main(String[] args) {
		SpringApplication.run(DwshopApplication.class, args);
	}

}
