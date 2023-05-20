package com.teodora.springcloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginapi")
public class LoginController {
	 @GetMapping("/login")
	    public String goToLoginPage() {
	        return "login";
	    }
}
