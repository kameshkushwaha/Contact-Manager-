package com.smartcontact.controlar;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String home(Model model) {

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
		// model.addAttribute("user", new User());

		return "success";

	}

	// this is a handeler for register

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1, Model model,
			HttpSession session, Massages massages) {
		try {
			if (result1.hasErrors()) {
				System.out.println("error-------" + result1.toString());
				// Add the user object to the model without saving it
				model.addAttribute("user", user);
				return "signup";
			}

			user.setEnable(true);
			user.setRole("Role-User");
			user.setImageUrl("fake-url.jpg");

			User result = this.userRepository.save(user);
			System.out.println("save data-----"+ result);

			// Clear the user object after successful registration
			model.addAttribute("user", new User());

			session.setAttribute("massages", new Massages("Successfully Register :)..", "alert-success"));
			
			session.removeAttribute("remove");

			
			return "signup";

		} catch (Exception e) {
			e.printStackTrace();
			// Add the user object to the model in case of exception
			model.addAttribute("user", user);
			session.setAttribute("massages", new Massages("Something went wrong: " + e.getMessage(), "alert-danger"));
			return "signup";
		}
	}

}
