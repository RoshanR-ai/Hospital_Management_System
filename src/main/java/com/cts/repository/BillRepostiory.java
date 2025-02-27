package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.model.Bill;
import com.cts.model.Patient;

import java.util.List;


@Repository
public interface BillRepostiory extends JpaRepository<Bill, Integer>{
	public List<Bill> findByPatient(Patient patient);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE db.bill SET payment_status = ?2 WHERE bill_id=?1",nativeQuery = true)
	public int updatePaymentStatus(Integer billId,String status);
	
	
	@Modifying
	@Transactional
	@Query(value="DELETE from db.bill WHERE patient_id=?1",nativeQuery = true)
	public int deleteAllByPatientId(Integer patientId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE from db.bill WHERE appointment_id IN (SELECT appointment_id FROM db.appointment WHERE doctor_id=?1)",nativeQuery = true)
	public int deleteAllByBillDoctorId(Integer doctorId);
}
