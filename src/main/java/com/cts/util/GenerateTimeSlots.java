package com.cts.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.cts.model.Doctor;
import com.cts.model.TimeSlot;

public class GenerateTimeSlots {
	
	private static final int SLOT_TIME = 15;
	
	public static LocalTime parseTime(String time) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
		return LocalTime.parse(time, format);
	}
	
	
	public static List<TimeSlot> generateTimeSlotsForDate(String schedule,LocalDate slotDate,Doctor doctor){
		String[] startAndEndTime = schedule.split("-");
		LocalTime startTime = parseTime(startAndEndTime[0]);
		LocalTime endTime = parseTime(startAndEndTime[1]);

		
		List<TimeSlot> slots = new ArrayList<>();
		while(startTime.plusMinutes(SLOT_TIME).isBefore(endTime) || startTime.plusMinutes(SLOT_TIME).equals(endTime)){
			TimeSlot slot = new TimeSlot();
			slot.setBooked(false);
			slot.setDoctor(doctor);
			slot.setSlotDate(slotDate);
			slot.setSlotTime(startTime);
			slots.add(slot);
			startTime = startTime.plusMinutes(SLOT_TIME);
		}
		
		return slots;	
	}
	public static List<TimeSlot> generateTimeSlotsForRangeOfDate(String schedule,LocalDate startDate,LocalDate endDate,Doctor doctor){
		String[] startAndEndTime = schedule.split("-");
		LocalTime startTime = parseTime(startAndEndTime[0]);
		LocalTime endTime = parseTime(startAndEndTime[1]);
		
		List<TimeSlot> slots = new ArrayList<>();
		while(startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			while(startTime.plusMinutes(SLOT_TIME).isBefore(endTime) || startTime.plusMinutes(SLOT_TIME).equals(endTime)) {
				TimeSlot slot = new TimeSlot();
				slot.setDoctor(doctor);
				slot.setSlotTime(startTime);
				slot.setSlotDate(startDate);
				slot.setBooked(false);
				
				slots.add(slot);
				startTime.plusMinutes(SLOT_TIME);
			}
			startDate.plusDays(1);
		}
		return slots;
	}
	
	public static List<LocalTime> generateTimeSlotStrings(String schedule){
		String[] startAndEndTime = schedule.split("-");
		LocalTime startTime = parseTime(startAndEndTime[0]);
		LocalTime endTime = parseTime(startAndEndTime[1]);
		List<LocalTime> slots = new ArrayList<>();
		while(startTime.plusMinutes(SLOT_TIME).isBefore(endTime) || startTime.plusMinutes(SLOT_TIME).equals(endTime)){
			slots.add(startTime);
			startTime = startTime.plusMinutes(SLOT_TIME);
		}
		return slots;	
	}
	
	public static List<TimeSlot> generateCustomTimeSlots(List<String> customSlots,LocalDate date,Doctor doctor){
		List<TimeSlot> slots = new ArrayList<>();
		for(String time:customSlots) {
			TimeSlot slot = new TimeSlot();
			slot.setSlotTime(parseTime(time));
			slot.setSlotDate(date);
			slot.setBooked(false);
			slot.setDoctor(doctor);
			slots.add(slot);
		}
		 return slots;
	}
	
}
