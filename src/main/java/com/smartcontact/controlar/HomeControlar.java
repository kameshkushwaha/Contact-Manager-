package com.smartcontact.controlar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartcontact.dao.UserRepository;
import com.smartcontact.entities.User;
//import org.springframework.web.bind.annotation.RestController;
import com.smartcontact.helper.Massages;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller
public class HomeControlar {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@RequestMapping("/home")
	public String home( Model model) {
		
		
		model.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
		
		
		
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title", "About-Smart Contact Manager");
		
		return "about";
		
		
		
	}
	@RequestMapping("/login")
	public String login(Model model) {
		
		model.addAttribute("title", "Log In-Smart Contact Manager");
		
		return "login";
		
		
		
	}
	@RequestMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("title", "Sign Up-Smart Contact Manager");
		model.addAttribute("user", new User());
		
		return "signup";
		
		}
	
	@RequestMapping("/success")
	public String success(Model model) {
		
		model.addAttribute("title", "Success-Smart Contact Manager");
		//model.addAttribute("user", new User());
		
		return "success";
		
		}
	
	
	
	// this is a handeler for register
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user ,Model model,HttpSession session,BindingResult result1) {
		
		try {
			
			if (result1.hasErrors()) {
				
				System.out.println("error"+ result1.toString());
				model.addAttribute("user",user);
				
				return "signup";
				
			}
			
			user.setEnable(true);
			user.setRole("Role-User");
			user.setImageUrl("fake-url.jpg");
			
			System.out.println("User--->"+user);
			
		    User result	= this.userRepository.save(user);
			System.out.println(result);
		    
			model.addAttribute("user", new User());
			
			session.setAttribute("massages", new Massages("Successfully Register :)..", "alert-success"));
			
			//session.removeAttribute("massages");
			

			return ("success");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("massages", new Massages("something went Wrong" +e.getMessage(), "alert-danger"));
			
			return "signup";
		}
		
		
		
		
	}
	
	
	
	
	
	

}
