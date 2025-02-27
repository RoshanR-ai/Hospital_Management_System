//package com.cts.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.cts.enums.UserRole;
//import com.cts.exception.InvalidCredentialsException;
//import com.cts.exception.UserNotFoundException;
//import com.cts.model.User;
//import com.cts.service.UserService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller	
//@RequestMapping("/user")
//public class UserController {
//	
//	
//	@Autowired
//	private UserService service;
//	
//	@GetMapping("/login")
//	public String getLoginForm(ModelMap map,HttpSession session) {
//		if(session.getAttribute("currentUser")==null) {			
//			map.addAttribute("loggedInUser",new User());
//			return "UserLoginForm";
//		}
//		return "AlreadyLoggedIn";
//	}
//	
//	
//	@PostMapping("/loginaction")
//	public String loginAction(@ModelAttribute("loggedInUser") User loggedInUser,HttpSession session,BindingResult bs) {
//		
//		try {
//		if(service.validateUserCredentials(loggedInUser.getUserName(),loggedInUser.getPassword())) {
//			
//			User user = service.getUserbyUserName(loggedInUser.getUserName());
//			session.setAttribute("currentUser", user);
//			UserRole role = user.getUserRole();
//	
//				if(role==UserRole.ADMIN) {
//					return "redirect:/admin";
//				}
//				else if(role == UserRole.DOCTOR){	
//					return "redirect:/doctor";
//				}
//				else {
//					return "redirect:/patient";
//				}
//			
//			}
//		}
////		catch(UserNotFoundException e){
////			bs.rejectValue("userName","error.loggedInUser",e.getMessage());
////		}
////		catch(InvalidCredentialsException e) {
////			bs.rejectValue("password","error.loggedInUser",e.getLocalizedMessage());
////		}
////		
////		if (bs.hasErrors()) {
////	        return "UserLoginForm";
////	    }
////		return "redirect:/user/login";
////	}
//	
//	
//	
//	@GetMapping("/register")
//	public String getRegisterForm() {
//		return "UserRegisterForm";
//	}
////	@GetMapping("/logout")
////	public String getLogout(HttpSession session) {
////		session.invalidate();
////		return "redirect:/user/login";
////	}
//	
//	
//	
//
//}
