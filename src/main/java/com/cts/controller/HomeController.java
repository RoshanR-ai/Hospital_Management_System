package com.cts.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.enums.UserRole;
import com.cts.exception.DuplicateUserException;
import com.cts.model.CustomUserDetails;
import com.cts.model.Patient;
import com.cts.model.User;
import com.cts.service.UserService;
import com.cts.util.MessagingService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/unauthorizedaccess")
	public String getUnauthorizedAccess(){
		return "UnAuthorizedAccess";
	}
	@GetMapping("/login")
	public String getLoginForm() {
		return "UserLoginForm";
	}
	@GetMapping("/logout")
	public String getLogout(){
		return "UserLoginForm";
	}
	@GetMapping("/changePassword")
	public String changePatientPassword(ModelMap model) {
		return "ChangePasswordForm";
	}
	
	@PostMapping("/changePassword/changePasswordAction")
	public String changePasswordAction(@RequestParam("currentPassword") String password,
									   @RequestParam("newPassword") String newPassword,
									   @RequestParam("confirmPassword") String confirmPassword,
									   Authentication auth,ModelMap model
									   ) {
		CustomUserDetails authUser = (CustomUserDetails) auth.getPrincipal();
		User user = userService.getUserbyUserId(authUser.getUserId());
		
		if(!newPassword.equals(confirmPassword)) {
			model.addAttribute("error","new password doesnot match with confirm password");
			return "ChangePasswordForm";
		}
		else if(!user.getPassword().equals(password)) {
			model.addAttribute("error","new password doesnot match with confirm password");
			return "ChangePasswordForm";
		}
		userService.changeUserPassword(newPassword, user.getUserName());
		model.addAttribute("role",user.getUserRole().name().toLowerCase());
		return "success";
	}
	
	@GetMapping("/register")
	public String getRegisterPatientForm(){
		return "RegisterAsPatientForm";
	}
	
	
	@PostMapping("/register/addPatient")
    public String validateNewPatient(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("patientName") String name,
            @RequestParam("dateOfBirth") Date dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("address") String address,
            Model model) {
		
        try {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserRole(UserRole.PATIENT);
        
        Patient patient = new Patient();
        patient.setPatientName(name);
        patient.setDateOfBirth(dateOfBirth);
        patient.setGender(gender);
        patient.setContactNumber(contactNumber);
        patient.setAddress(address);
        
        patient.setUser(user);
        user.setPatient(patient);
        
        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);
        }
        catch(DuplicateUserException e) {
        	return "redirect:/register?error=true";
        }
        return "redirect:/login";
    }
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
