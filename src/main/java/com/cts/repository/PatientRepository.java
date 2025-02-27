package com.cts.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.model.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findById(int id);
    
    @Query(value="SELECT * FROM db.patient where user_id=?1",nativeQuery=true)
    public Patient findByUserId(int userId);
    
    
    @Modifying
    @Transactional
    @Query(value="UPDATE db.patient SET medical_history= :medicalHistory where patient_id=:patientId",nativeQuery = true)
    public int updateMedicalHistory(@Param("medicalHistory") String medicalHistory,@Param("patientId")Integer patientId);
}