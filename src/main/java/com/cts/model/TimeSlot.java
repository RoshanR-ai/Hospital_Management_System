package com.cts.model;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="time_slot")
public class TimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slot_id")
	private Integer slotId;
	
	@Column(name="slot_time")
	private LocalTime slotTime;
	
	@Column(name="slot_date")
	private LocalDate slotDate;
	
	@Column(name="is_booked")
	private boolean isBooked;
	
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name = "doctor_id", nullable = false)
	 private Doctor doctor;

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public LocalTime getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(LocalTime slotTime) {
		this.slotTime = slotTime;
	}

	public LocalDate getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(LocalDate slotDate) {
		this.slotDate = slotDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}	
	
}
