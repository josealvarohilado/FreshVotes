package com.freshvotes.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login") // short version of @RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
