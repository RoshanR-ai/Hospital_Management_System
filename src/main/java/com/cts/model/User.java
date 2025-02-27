package com.cts.model;

import com.cts.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	
	@Column(name="user_name",nullable=false,unique=true,length=50)
	private String userName;
	
	@Column(name="email",nullable=false,unique=true,length=50)
	private String email;
	
	
	@Column(name="password",nullable=false,length=255)
	private String password;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_role",nullable=false)
	private UserRole userRole;
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true)
	private Doctor doctor;
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true)
	private Patient patient;
	
	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getUserId() {
		return userId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public boolean isAdmin() {
		return this.userRole == UserRole.ADMIN;
	}
	public boolean isPatient() {
		return this.userRole == UserRole.PATIENT;
	}
	public boolean isDoctor() {
		return this.userRole == UserRole.DOCTOR;
	}

//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userRole=" + userRole
//				+ "]";
//	}


}
