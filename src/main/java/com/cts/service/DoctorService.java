package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.exception.DoctorNotFoundException;
import com.cts.model.Doctor;
import com.cts.repository.DoctorRepository;

@Service
public class DoctorService {
		
	@Autowired
	DoctorRepository repo;
	
	public Doctor saveDoctor(Doctor doctor) {
		return repo.save(doctor);
	}
	
	public List<Doctor> getAllDoctors(){
		List<Doctor> doctor =  repo.findAll();
		return doctor;
	}
	
	public Doctor getDoctorById(int doctorId) throws DoctorNotFoundException {
		Doctor doctor =  repo.findById(doctorId);
		if(doctor==null) {
			throw new DoctorNotFoundException("No Doctor found with the given Doctor Id");
		}
		return doctor;
	}
	
	public Doctor getDoctorByUserId(int userId) {
		return repo.findByUserId(userId);
	}
	
	public Doctor updateDoctor(Doctor doctor) {
		return repo.save(doctor);
	}
	public void deleteDoctor(int doctorId) {
		repo.deleteById(doctorId);
	}
}
