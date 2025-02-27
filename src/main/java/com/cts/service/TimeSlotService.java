package com.cts.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Doctor;
import com.cts.model.TimeSlot;
import com.cts.repository.TimeSlotRepository;

@Service
public class TimeSlotService{
	
	@Autowired
	private TimeSlotRepository repo;
	
	public TimeSlot getTimeSlotById(Integer slotId) {
		return repo.getById(slotId);
	}
	
	public List<TimeSlot> saveAllSlots(List<TimeSlot> slots){
		return repo.saveAll(slots);
	}
	
	public List<TimeSlot> getAllSlotsOfDoctor(Doctor doctor) throws Exception{
		 List<TimeSlot> slots  = repo.findByDoctor(doctor);
		 return slots;
	}
	public List<TimeSlot> getAllSlotsOfDoctorById(Integer doctorId){
		return repo.findByDoctorId(doctorId);
	}
	
	public List<TimeSlot> getAllSlots(){
		return repo.findAll();
	}
	public List<TimeSlot> getAllUnBookedSlotsOfDoctor(Integer doctorId){
		return repo.findByDoctorIdNotBooked(doctorId);
	}
	public boolean bookSlot(Integer slotId) {
		 int rowsAffected = repo.bookSlot(slotId);
		 if(rowsAffected==1) {
			 return true;
		 }
		 return false;
	}
	public boolean unBookSlot(LocalDate slotDate,LocalTime slotTime) {
		 int rowsAffected = repo.unBookSlot(slotDate,slotTime);
		 if(rowsAffected==1) {
			 return true;
		 }
		 return false;
	}
	
	public void unBookPatientSlots(Integer PatientId) {
		repo.unBookSlotForPatient(PatientId);
	}
	
}
