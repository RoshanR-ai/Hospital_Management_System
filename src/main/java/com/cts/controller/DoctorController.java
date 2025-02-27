package com.cts.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.enums.PaymentStatus;
import com.cts.enums.UserRole;
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
import com.cts.util.GenerateTimeSlots;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;


@Controller
@RequestMapping("/doctor")
@SessionAttributes({"currentDoctorUserId","currentDoctor"})
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private BillService billService;
	
	@ModelAttribute("currentDoctorUserId")
	public Integer getCurrentUserId(Authentication auth) {
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		return user.getUserId();
	}
	
	@ModelAttribute("currentDoctor")
	public Doctor getCurrentPatient(@ModelAttribute("currentDoctorUserId") Integer userId) {
		return doctorService.getDoctorByUserId(userId);
	}
	
	@GetMapping
	public String getDoctorDashboard(ModelMap model,Authentication auth) {

		return "DoctorDashboard";
	}
	
	@GetMapping("/generateAppointments")
	public String getGenerateAppointments(ModelMap model){
		Doctor doctor = (Doctor) model.getAttribute("currentDoctor");
		List<LocalTime> slots = GenerateTimeSlots.generateTimeSlotStrings(doctor.getAvailabilitySchedule()); 
		model.addAttribute("slots",slots);
		return "GenerateAppoinmentsForm";
	}
	@PostMapping("/generateAppointments/generateCustomSlots")
	public String generate(@RequestParam("customDate") String dateString,@RequestParam("selectedSlots") List<String> selectedSlots,Authentication auth,ModelMap model){
		LocalDate date = LocalDate.parse(dateString);
		Doctor doctor = (Doctor) model.getAttribute("currentDoctor");
		List<TimeSlot> slots = GenerateTimeSlots.generateCustomTimeSlots(selectedSlots,date, doctor);
		timeSlotService.saveAllSlots(slots);
		return "redirect:/doctor/generateAppointments";
	}
	@PostMapping("/generateAppointments/generate")
	public String generate(@RequestParam("DateToGenerate") String dateString,Authentication auth){
         try {
			LocalDate date = LocalDate.parse(dateString);
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Doctor doctor = doctorService.getDoctorByUserId(user.getUserId());
			List<TimeSlot> slots = GenerateTimeSlots.generateTimeSlotsForDate(doctor.getAvailabilitySchedule(), date,doctor);
			timeSlotService.saveAllSlots(slots);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "redirect:/doctor/generateAppointments";
	}
	@PostMapping("/generateAppointments/generateForRange")
	public String generate(@RequestParam("startDate") String startDate,
						   @RequestParam("endDate") String endDate,
						   Authentication auth,ModelMap model){
		LocalDate localStartDate = LocalDate.parse(startDate);
		LocalDate localEndDate = LocalDate.parse(endDate);
		
		if(localEndDate.isBefore(localStartDate)) {
			model.addAttribute("error","End date cannot be before start date");
			return "redirect:/doctor/generateAppointments";
		}
		
		
		try {
			
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Doctor doctor = doctorService.getDoctorByUserId(user.getUserId());
			List<TimeSlot> slots = GenerateTimeSlots.generateTimeSlotsForRangeOfDate(doctor.getAvailabilitySchedule(), localStartDate, localEndDate, doctor);
			timeSlotService.saveAllSlots(slots);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "redirect:/doctor/generateAppointments";
	}
	
//	@PostMapping("/generateAppointments/generateCustomSlots")
//	public String generate(Authentication auth){
//         try {
//			LocalDate date = LocalDate.parse(dateString);
//			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
//			Doctor doctor = doctorService.getDoctorByUserId(user.getUserId());
//			List<TimeSlot> slots = GenerateTimeSlots.generateTimeSlotsForDate(doctor.getAvailabilitySchedule(), date,doctor);
//			timeSlotService.saveAllSlots(slots);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		return "redirect:/doctor/generateAppointments";
//	}
	
	@GetMapping("/upcommingAppointments")
	public String getUpcommingAppointments(ModelMap model) {
		Doctor doctor = (Doctor) model.getAttribute("currentDoctor");
		List<Appointment> upcommingAppointments = appointmentService.getUpcommingAppointmentsByDoctorId(doctor.getDoctorId());
		model.addAttribute("upcommingAppointments",upcommingAppointments);
		return "UpcommingAppointmentsForDoctor";
	}
	
	@GetMapping("/upcommingAppoitment/cancelAppointment")
	public String cancelAppointment(@RequestParam("appointmentId") Integer appointmentId) {
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		boolean isCancelled = appointmentService.updateAppointmentStatusAsCancelled(appointmentId);
		if(isCancelled){
			timeSlotService.unBookSlot(appointment.getAppointmentDate(),appointment.getTimeSlot());
			return "redirect:/doctor/upcommingAppointments";
		}
		return "/error";
	}
	
	@GetMapping("/updateMedicalHistory")
	public String updateHistory(ModelMap model){
		Doctor doctor = (Doctor) model.getAttribute("currentDoctor");
		System.out.println(doctor.toString());
		List<Appointment> appointments = appointmentService.getAppointmentsForMedicalHistoryUpdate(doctor.getDoctorId());
		model.addAttribute("appointments",appointments);
		return "UpdateMedicalHistory";
	}
	@GetMapping("/updateMedicalHistory/{appointmentId}")
	public String updateHistory(@PathVariable("appointmentId") Integer appointmentId,ModelMap model){
		System.out.print("aId"+appointmentId);
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		Patient patient = patientService.getPatientById(appointment.getPatient().getPatientId());
		model.addAttribute("appointmentId", appointmentId);
		model.addAttribute("patient",patient);
		return "UpdateMedicalHistoryForm";
	}
	
	@PostMapping("/updateMedicalHistory/update")
	public String updatePatient(@RequestParam("appointmentId") Integer appointmentId,
								@RequestParam("patientId") Integer patientId,
								@RequestParam("medicalHistory") String medicalHistory,
								ModelMap model) {
		System.out.println("aId"+appointmentId);
		System.out.println("pId"+patientId);
		boolean isPatientUpdated = patientService.updatePatientMedicalHistory(patientId, medicalHistory);
		boolean isAppointmentUpdated = appointmentService.updateAppointmentStatusAsCompleted(appointmentId);
		if(isPatientUpdated && isAppointmentUpdated) {
			Bill bill = new Bill();
			bill.setBillDate(LocalDate.now());
			bill.setPaymentStatus(PaymentStatus.UNPAID);
			bill.setTotalAmount((float) 1000.00);
			bill.setPatient(patientService.getPatientById(patientId));
			bill.setAppointment(appointmentService.getAppointmentById(appointmentId));
			billService.saveBill(bill);
			
		}
		return "redirect:/doctor/updateMedicalHistory";
	}
	
	@GetMapping("/completedConsultation")
	public String getCompletedConsultation(ModelMap model) {
		Doctor doctor = (Doctor) model.getAttribute("currentDoctor");
		List<Appointment> completedAppointments = appointmentService.getCompletedAppointmentsByDoctorId(doctor.getDoctorId());
		model.addAttribute("completedAppointments",completedAppointments);
		return "CompletedConsultation";
	}
	
}