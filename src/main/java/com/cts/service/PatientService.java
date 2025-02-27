package com.cts.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Patient;
import com.cts.repository.PatientRepository;
import com.cts.exception.PatientNotFoundException;;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository repo;
	
	public Patient savePatient(Patient patient) {
		return repo.save(patient);
	}
	
	public List<Patient> getAllPatients(){
		return repo.findAll();
	}
	
	public Patient getPatientById(int id) {
		Patient patient =  repo.findById(id);
		if(patient==null) {
			throw new PatientNotFoundException("No Patient found with the given patient Id");
		}
		return patient;
	}
	public void deletePatient(int patientId) {
		repo.deleteById(patientId);
	}
	
	public Patient getPatientByUserId(int userId) {
		Patient patient =  repo.findByUserId(userId);
		if(patient==null) {
			throw new PatientNotFoundException("No Patient found with the given user Id");
		}
		return patient;
	}
	public boolean updatePatientMedicalHistory(Integer patientId,String medicalHistory) {
		int rowAffected = repo.updateMedicalHistory(medicalHistory, patientId);
		if(rowAffected>0) {
			return true;
		}
		return false;
	}
}
