package com.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.model.User;
import com.demo.api.repo.UserRegistrationandloginRepository;


/*Author : - Suyash Kadam
 * 29-12-2024
 * */


@Controller
public class UserController {

	
	 @Autowired 
	 private UserRegistrationandloginRepository userRepo;
	  
	  @GetMapping("") 
	  public String viewHomePage() 
	  { return "index";
	  
	  }
	  
	  
	  
	  @GetMapping("/register")
	  public String showSignUpForm(Model model) {
	  
	  model.addAttribute("user" , new User()); 
	  return "signup_form";
	  
	  }
	 
		/*
		 * @GetMapping("/register") public ResponseEntity<String> showSignUpForm(Model
		 * model) {
		 * 
		 * model.addAttribute("user" , new User()); String s="signup_form"; return
		 * ResponseEntity.ok(s);
		 * 
		 * }
		 */

	  @PostMapping("/process_register")
	  public String processRegistration(User user ) {
		
		  BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		 String encodedPassword=encoder.encode(user.getPassword());
		 user.setPassword(encodedPassword);
		 userRepo.save(user);
		  return "register_success";
		  
	  }
	  
	  @GetMapping("/list_users")
	  public String viewUsersList(Model model) {
		  List<User>listUsers=userRepo.findAll();
		  model.addAttribute("listUsers", listUsers);
		return "users";
		  
	  }
	  
}
