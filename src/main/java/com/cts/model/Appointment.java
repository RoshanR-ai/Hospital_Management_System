package com.cts.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.cts.enums.Status;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Column(name = "appointmentDate")
    private LocalDate appointmentDate;

    @Column(name = "timeSlot", length = 20)
    private LocalTime timeSlot;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @OneToOne(mappedBy="appointment")
    private Bill bill;

    // Getters and Setters
    public Integer getAppointmentId() {
        return appointmentId;
    }

    
    public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
    	this.appointmentDate = appointmentDate;
      }

    public LocalTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    
    
//    @Override
//    public String toString() {
//        return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient + ", doctor=" + doctor
//                + ", appointmentDate=" + appointmentDate + ", timeSlot=" + timeSlot + ", status=" + status + "]";
//    }
}