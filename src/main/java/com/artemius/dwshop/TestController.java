package com.artemius.dwshop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artemius.dwshop.entities.Deps;
import com.artemius.dwshop.repositories.DepsRepository;
import com.artemius.dwshop.services.DepsService;

import lombok.*;


@Controller
public class TestController {
    @Autowired
    DepsService depsService;
    
    
    @GetMapping("/test")
    public String test(@RequestParam(name = "message",required = false,defaultValue = "BLANK MESSAGE HERE") String message,
	    Model model) {
	List<String> depsNames = new ArrayList<>();
	List<Deps> deps = depsService.selectAllDeps();
	/*for (Deps d: deps) {
	    depsNames.add(d.getName());
	}*/

	model.addAttribute("message",message);
	model.addAttribute("testDb",depsNames);
	return "test";
    }
}
