package com.cts.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	public List<Doctor> findAll();
	public Doctor findById(int id);
    
	@Query(value="SELECT * FROM doctor WHERE user_id =?1", nativeQuery = true)
	public Doctor findByUserId(int userId);
	

}
