package com.cts.controller;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.enums.UserRole;
import com.cts.exception.DuplicateUserException;
import com.cts.model.Appointment;
import com.cts.model.Bill;
import com.cts.model.CustomUserDetails;
import com.cts.model.Doctor;
import com.cts.model.Patient;
import com.cts.model.User;
import com.cts.service.AppointmentService;
import com.cts.service.BillService;
import com.cts.service.DoctorService;
import com.cts.service.PatientService;
import com.cts.service.TimeSlotService;
import com.cts.service.UserService;
import com.cts.validator.CustomValidator;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"adminName"})
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
    private PatientService patientService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private BillService billService;
	
	@Autowired
	private TimeSlotService timeSlotSerice;
	
	
	
	@ModelAttribute("adminName")
	public String getAdministratorName(Authentication auth) {
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		return user.getUsername();
	}
	
	@GetMapping
	public String getAdminDashboard() {
		return "AdminDashboard";
	}
	
	@GetMapping("/addAdmin")
	public String getAddAdminForm() {
		return "AddAdminForm";
	}	
	@PostMapping("/addAdmin/add")
	public String addAdmin(@RequestParam("userName") String username,
							@RequestParam("email") String email,
							@RequestParam("password") String password,
							@RequestParam("confirmPassword") String confirmPassword,
							ModelMap model) {
		
		if(!password.equals(confirmPassword)) {
			model.addAttribute("error","The passwords do not match");
			return "AddAdminForm";
		}
		else if(userService.getUserbyUserName(username)!=null){
			model.addAttribute("error","Username isn't Available");
			return "AddAdminForm";
		}
		User user = new User();
		user.setUserName(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setUserRole(UserRole.ADMIN);
		user = userService.saveUser(user);
		
		if(user==null) {
			return "/error";
		}
		return "redirect:/admin";
		
	}
	
	@GetMapping("/manageDoctors")
	public String getManageDoctorsPage(HttpSession session,ModelMap model) {
		List<Doctor> doctors = doctorService.getAllDoctors();
		model.addAttribute("doctors",doctors);
		return "ManageDoctors";
	}
	
	@GetMapping("/manageDoctors/addDoctor")
	public String getAddDoctorsPage(HttpSession session) {
		return "AddDoctorForm";
	}
	
	
	@PostMapping("manageDoctors/addDoctor/validateNewDoctor")
    public String validateNewDoctor(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("doctorName") String name,
            @RequestParam("specialization") String specialization,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            Model model) {
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserRole(UserRole.DOCTOR);
		
		Doctor doctor = new Doctor();
		doctor.setDoctorName(name);
		doctor.setSpecialization(specialization);
		doctor.setContactNumber(contactNumber);
		String availablitySchedule = startTime+"-"+endTime;
		doctor.setAvailabilitySchedule(availablitySchedule);
		
		doctor.setUser(user);
		user.setDoctor(doctor);
		
		try {
			userService.saveUser(user);
		}
		catch(DuplicateUserException ex) {
			model.addAttribute("errorMessage",ex.getMessage());
			return "AddDoctorForm";
		}
		
		
		return "redirect:/admin/manageDoctors";
		
	}
	@GetMapping("/manageDoctors/updateDoctor/{id}")
	public String getAddDoctorsPage(@PathVariable int id,HttpSession session,ModelMap model) {
		Doctor updateDoctor = doctorService.getDoctorById(id);
		model.addAttribute("doctor",updateDoctor);
		return "UpdateDoctorForm";
	}
	
    @PostMapping("/manageDoctors/updateDoctor")
    public String updateDoctor(
    		@RequestParam("doctorId") int doctorId,
    		@RequestParam("doctorName") String name,
    		@RequestParam("specialization") String specialization,
    		@RequestParam("contactNumber") String contactNumber,
    		@RequestParam("availabilitySchedule") String availabilitySchedule) {
    	
    	Doctor doctor = doctorService.getDoctorById(doctorId);
    	doctor.setDoctorName(name);
    	doctor.setSpecialization(specialization);
    	doctor.setContactNumber(contactNumber);
    	doctor.setAvailabilitySchedule(availabilitySchedule);
    	
    	doctorService.saveDoctor(doctor);
    	
    	return "redirect:/admin/manageDoctors";
    }
    
    @GetMapping("/manageDoctors/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable("id") Integer doctorId) {
    	billService.deleteBillsByAppointmentDoctorId(doctorId);    	
    	appointmentService.deleteAppointmentsByDoctorId(doctorId);
    	doctorService.deleteDoctor(doctorId);  	
    	return "redirect:/admin/manageDoctors";
    }

    

	@GetMapping("/managePatients")
    public String getManagePatientsPage(HttpSession session, ModelMap model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "ManagePatients";
    }
    
    @GetMapping("/managePatients/addPatient")
    public String getAddPatientsPage(HttpSession session) {
        return "AddPatientForm";
    }
    
    @PostMapping("/managePatients/addPatient/validateNewPatient")
    public String validateNewPatient(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("patientName") String name,
            @RequestParam("dateOfBirth") Date dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("address") String address,
            @RequestParam("medicalHistory") String medicalHistory,
            Model model) {
        
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
        patient.setMedicalHistory(medicalHistory);
        
        patient.setUser(user);
        user.setPatient(patient);
        
        userService.saveUser(user);
        
        return "redirect:/admin/managePatients";
    }
    
    @GetMapping("/managePatients/updatePatient/{id}")
    public String getUpdatePatientsPage(@PathVariable int id, HttpSession session, ModelMap model) {
        Patient updatePatient = patientService.getPatientById(id);
        model.addAttribute("patient", updatePatient);
        return "UpdatePatientForm";
    }
    
    @PostMapping("/managePatients/updatePatient")
    public String updatePatient(
            @RequestParam("patientId") int patientId,
            @RequestParam("patientName") String name,
            @RequestParam("dateOfBirth") Date dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("address") String address,
            @RequestParam("medicalHistory") String medicalHistory) {
        
        Patient patient = patientService.getPatientById(patientId);
        patient.setPatientName(name);
        patient.setDateOfBirth(dateOfBirth);
        patient.setGender(gender);
        patient.setContactNumber(contactNumber);
        patient.setAddress(address);
        patient.setMedicalHistory(medicalHistory);
        
        patientService.savePatient(patient);
        
        return "redirect:/admin/managePatients";
    } 
    
    
    @GetMapping("/managePatients/deletePatient/{id}")
    @Transactional
    public String deletePatient(@PathVariable int id) {
    	//newly added code 27/02/2025
    	timeSlotSerice.unBookPatientSlots(id);
    	billService.deleteBillsByPatientId(id);
    	appointmentService.deleteAppointmentsByPatientId(id);
    	patientService.deletePatient(id);  	
    	return "redirect:/admin/managePatients";
    }
    
    @GetMapping("/manageAppointments")
    public String getManageAppointments(ModelMap model) {
    	List<Appointment> appointments = appointmentService.getAllAppointments();
    	model.addAttribute("appointments",appointments);
    	return "ManageAppointments";
    }
	
    @GetMapping("/manageBills")
    public String manageBills(Model model) {
    	List<Bill> bills = billService.getAllBills();
    	model.addAttribute("bills",bills);
    	return "ManageBills";
    	
    }
    @PostMapping("/manageBills/updatePaymentStatus")
    public String manageBills(@RequestParam("billId") Integer billId,ModelMap model) {
    	boolean isUpdated = billService.updatePaymentStatusAsPaid(billId);
    	if(isUpdated) {
    		return "redirect:/admin/manageBills";
    	}
    	return "error";
    }

}
