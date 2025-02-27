package com.cts.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	@Column(name="doctor_name",length=100)
	private String doctorName;
	@Column(name="specialization",length=100)
	private String specialization;
	@Column(name="contact_number",length=15)
	private String contactNumber;
	@Column(name="availability_schedule")
	private String availabilitySchedule;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;
	
	 @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
	 private List<TimeSlot> timeSlots;
	 
	 @OneToMany(mappedBy= "doctor",cascade=CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
	 private List<Appointment> appointments;
	 
	
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	
	
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getAvailabilitySchedule() {
		return availabilitySchedule;
	}
	public void setAvailabilitySchedule(String availabilitySchedule) {
		this.availabilitySchedule = availabilitySchedule;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
//	@Override
//	public String toString() {
//		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", specialization=" + specialization
//				+ ", contactNumber=" + contactNumber + ", availabilitySchedule=" + availabilitySchedule + ", user="
//				+ user + ", timeSlots=" + timeSlots + ", appointments=" + appointments + "]";
//	}
	
}
