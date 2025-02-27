package com.cts.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cts.service.AppointmentService;

@Component
public class AppointmentScheduler {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Scheduled(cron = "0 0/15 * * * ?")
	public void runScheduledTask() {
		System.out.print("It runs");
		appointmentService.confirmedToMedicalHistoryUpdate();
	}
}
