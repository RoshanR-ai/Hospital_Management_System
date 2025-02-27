package com.cts.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.enums.Status;
import com.cts.model.Appointment;
import com.cts.model.Bill;
import com.cts.model.CustomUserDetails;
import com.cts.model.Doctor;
import com.cts.model.Patient;
import com.cts.model.TimeSlot;
import com.cts.model.User;
import com.cts.service.AppointmentService;
import com.cts.service.BillService;
import com.cts.service.DoctorService;
import com.cts.service.PatientService;
import com.cts.service.TimeSlotService;
import com.cts.service.UserService;
import com.cts.util.MessagingService;
import com.cts.util.PdfGenerator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.websocket.server.PathParam;


@Controller
@RequestMapping("/patient")
@SessionAttributes({"currentPatientUserId","currentPatient"})
public class PatientController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired 
	private AppointmentService appointmentService;
	
	@Autowired
	private BillService billService;
	
	
	@ModelAttribute("currentPatientUserId")
	public Integer getCurrentUserId(Authentication auth) {
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		return user.getUserId();
	}
	
	@ModelAttribute("currentPatient")
	public Patient getCurrentPatient(@ModelAttribute("currentPatientUserId") Integer userId) {
		return patientService.getPatientByUserId(userId);
	}
	
	@GetMapping
	public String getAdminDashboard(Authentication auth,ModelMap model) {
		return "PatientDashboard";
	}
	
	
	@GetMapping("/bookAppointments")
	public String getAppoinments(ModelMap model){
		List<Doctor> doctors = doctorService.getAllDoctors();
		model.addAttribute("doctors",doctors);
		return "BookAppointments";
	}
	

	@GetMapping("/bookAppointments/viewTimeSlots")
	public String getSlots(@RequestParam("doctorId") Integer doctorId, ModelMap model) {
	    List<TimeSlot> slots= timeSlotService.getAllUnBookedSlotsOfDoctor(doctorId);
	    Map<String,List<TimeSlot>> slotMap = new HashMap<>();
	    for(TimeSlot slot:slots) {
	    	String day = slot.getSlotDate().toString()+","+slot.getSlotDate().getDayOfWeek().toString();
	    	if(!slotMap.containsKey(day)) {
	    		slotMap.put(day, new ArrayList<TimeSlot>());
	    	}
	    	slotMap.get(day).add(slot);
	    }
	    
	    
	    model.addAttribute("slotMap",slotMap);
	    return "ViewTimeSlots";
	}

	@PostMapping("/bookAppointments/viewTimeSlots/selectSlot")
	public String bookAppointment(@RequestParam("selectedSlotId") Integer slotId,
								  @ModelAttribute("currentPatient") Patient patient,
								  ModelMap model) {
		TimeSlot slot = timeSlotService.getTimeSlotById(slotId);
		Doctor doctor = slot.getDoctor();
		Appointment newAppointment = new Appointment();
		newAppointment.setAppointmentDate(slot.getSlotDate());
		newAppointment.setTimeSlot(slot.getSlotTime());
		newAppointment.setStatus(Status.CONFIRMED);
		newAppointment.setDoctor(doctor);
		newAppointment.setPatient(patient);
		newAppointment = appointmentService.saveAppointment(newAppointment);
		if(newAppointment==null) {
			return "redirect:/error";
		}
		timeSlotService.bookSlot(slotId);
		return "success";
	}
	
	@GetMapping("/upcommingAppointments")
	public String getUpcommingAppointments(ModelMap model) {
		Patient patient = (Patient) model.getAttribute("currentPatient");
		List<Appointment> appointmentLog = appointmentService.getUpcommingAppointmentsByPatientId(patient.getPatientId());
		model.addAttribute("appointments",appointmentLog);
		return "UpcommingAppointments";
	}
	
	@GetMapping("/upcommingAppointments/cancel")
	public String getUpcommingAppointments(@RequestParam("appointmentId")Integer appointmentId) {
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		boolean isCancelled = appointmentService.updateAppointmentStatusAsCancelled(appointmentId);
		if(isCancelled) {
			timeSlotService.unBookSlot(appointment.getAppointmentDate(),appointment.getTimeSlot());
		return "redirect:/patient/upcommingAppointments";
		}
		return "/error";
	}
	
	@GetMapping("/appointmentHistory")
	public String getConsultationHistory(ModelMap model) {
		Patient patient = (Patient) model.getAttribute("currentPatient");
		List<Appointment> appointmentLog = appointmentService.getAppointmentHistoryByPatientId(patient.getPatientId());
		model.addAttribute("appointments",appointmentLog);
		return "AppointmentHistory";
	}
	
	
	
	
	@GetMapping("/billing")
	public String getBilling(@ModelAttribute("currentPatient") Patient patient,ModelMap model) {
		List<Bill> bills = billService.getAllBillsOfPatient(patient); 
		System.out.println(bills);
		model.addAttribute("bills",bills);
		return "PatientBilling";
	}
	@GetMapping("/billing/paybill")
	public String getBilling(@RequestParam("billId") Integer billId) {
		System.out.println("gets here");
		boolean isUpdated = billService.updatePaymentStatusAsPaid(billId);
		if(isUpdated) {
			return "success";
		}
		return "redirect:/error";
	}
	
	@GetMapping("/billing/invoice")
	public String getInvoice(@RequestParam("billId") Integer billId,ModelMap model,HttpServletResponse response,HttpServletRequest request) {
		
		Bill bill = billService.getBillbyId(billId);
		model.addAttribute("patientName",bill.getPatient().getPatientName());
		
		try {
			String htmlContent = PdfGenerator.renderJsptoHtml(response,request,"/WEB-INF/jsp/Invoice.jsp");
			PdfGenerator.generatePdfFromJsp(response, htmlContent, "patient_report.pdf");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/patient/billing";
	}
}

