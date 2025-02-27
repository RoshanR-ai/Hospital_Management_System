package com.cts.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.enums.Status;
import com.cts.model.Appointment;
import com.cts.repository.AppointmentRepository;


@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repo;

    public Appointment saveAppointment(Appointment appointment) {
        return repo.save(appointment);
    }
    
    public Appointment getAppointmentById(Integer appointmentId) {
    	return repo.getById(appointmentId);
    }
    
    public List<Appointment> getAppointmentHistoryByPatientId(Integer patientId){
    	return repo.findByAppointmentHistoryPatientId(patientId);
    }

    public List<Appointment> getAppointmentsForMedicalHistoryUpdate(Integer doctorId) {
        return repo.findByDoctorIdAndStatus(Status.MEDICAL_HISTORY_UPDATE.name(),doctorId);
    }
    
    public List<Appointment> getUpcommingAppointmentsByDoctorId(Integer doctorId) {
        return repo.findUpcommingAppointmentsByDoctorId(doctorId);
    }
    
    public boolean updateAppointmentStatusAsCompleted(Integer appointmentId) {
    	int rowAffected = repo.updateStatus(Status.COMPLETED.name(), appointmentId);
    	if(rowAffected>0) {
    		return true;
    	}
    	return false;
    }
    
    public void confirmedToMedicalHistoryUpdate() {
    	repo.cnfMedHisUpdate();
    }
    
    public List<Appointment> getAllAppointments(){
    	return repo.findAll();
    }

    public List<Appointment> getUpcommingAppointmentsByPatientId(Integer patientId) {
		return repo.findUpcommingAppointmentsByPatientId(patientId);
	}
    
    public boolean updateAppointmentStatusAsCancelled(Integer appointmentId) {
    	int rowAffected = repo.updateStatus(Status.CANCELLED.name(), appointmentId);
    	if(rowAffected>0) {
    		return true;
    	}
    	return false;
    }
    
    public List<Appointment> getCompletedAppointmentsByDoctorId(Integer doctorId){
    	return repo.findCompletedAppointmentsByDoctorId(doctorId);
    }
    
    public void deleteAppointmentsByPatientId(Integer patientId) {
    	repo.deleteAllByPatientId(patientId);
    }
	public void deleteAppointmentsByDoctorId(Integer doctorId) {
		repo.deleteAllByDoctorId(doctorId);
	}
}
