package br.edu.ifpr.sgta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/error")
	public String error() {
		return "login";
	}
}
