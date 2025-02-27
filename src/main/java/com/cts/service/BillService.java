package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.enums.PaymentStatus;
import com.cts.model.Bill;
import com.cts.model.Patient;
import com.cts.repository.BillRepostiory;

@Service
public class BillService {
	
	@Autowired
	private BillRepostiory repo;
	
	public Bill saveBill(Bill bill) {
		return repo.save(bill);
	}
	
	public List<Bill> getAllBills() {
		return repo.findAll();
	}

	public List<Bill> getAllBillsOfPatient(Patient patient){
		return repo.findByPatient(patient);
	}
	public boolean updatePaymentStatusAsPaid(Integer billId) {
		int rowsAffected = repo.updatePaymentStatus(billId, PaymentStatus.PAID.name());
		if(rowsAffected>0) {
			return true;
		}
		return false;
	}
	public Bill getBillbyId(Integer billId) {
		return repo.getById(billId);
	}
	public void deleteBillsByPatientId(Integer patientId) {
		repo.deleteAllByPatientId(patientId);
	}
	public void deleteBillsByAppointmentDoctorId(Integer doctorId) {
		repo.deleteAllByBillDoctorId(doctorId);
	}
}
