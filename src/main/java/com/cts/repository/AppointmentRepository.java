package com.cts.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.model.Appointment;



import java.util.List;
import com.cts.enums.Status;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	public List<Appointment> findByStatus(Status status);

	
	@Query(value="SELECT * FROM db.appointment WHERE doctor_id=?2 AND status=?1",nativeQuery=true)
	public List<Appointment> findByDoctorIdAndStatus(String status,Integer doctorId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM db.appointment WHERE patient_id=?1", nativeQuery=true)
	public int deleteAllByPatientId(Integer patientId);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM db.appointment WHERE doctor_id=?1", nativeQuery=true)
	public int deleteAllByDoctorId(Integer patientId);
	
	
	@Modifying
	@Transactional
	@Query(value="UPDATE db.appointment SET status = ?1 WHERE appointment_id = ?2;", nativeQuery=true)
	public int updateStatus(String status,Integer appointmentId);
	
	
	
	@Modifying
	@Transactional
	@Query(value="UPDATE db.appointment SET status=\"MEDICAL_HISTORY_UPDATE\" \r\n"
			+ "WHERE status='CONFIRMED' AND TIMESTAMPADD(MINUTE,15,TIMESTAMP(appointment_date,time_slot))<= NOW()",nativeQuery = true)
	public void cnfMedHisUpdate();
	
	
	@Query(value="SELECT * FROM db.appointment WHERE patient_id=?1 AND status NOT IN ('CONFIRMED') ORDER BY appointment_date,time_slot",nativeQuery=true)
	public List<Appointment> findByAppointmentHistoryPatientId(Integer patientId);
	
	@Query(value="SELECT * FROM db.appointment WHERE patient_id=?1 AND status IN ('CONFIRMED') ORDER BY appointment_date,time_slot",nativeQuery=true)
	public List<Appointment> findUpcommingAppointmentsByPatientId(Integer patientId);
	
	@Query(value="SELECT * FROM db.appointment WHERE doctor_id=?1 AND status IN ('COMPLETED') ORDER BY appointment_date,time_slot",nativeQuery=true)
	public List<Appointment> findCompletedAppointmentsByDoctorId(Integer doctorId);
	
	@Query(value="SELECT * FROM db.appointment WHERE doctor_id=?1 AND status IN ('CONFIRMED') ORDER BY appointment_date,time_slot",nativeQuery=true)
	public List<Appointment> findUpcommingAppointmentsByDoctorId(Integer doctorId);
	
	
	}
