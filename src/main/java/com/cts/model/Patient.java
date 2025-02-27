package com.cts.model;

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
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @Column(name="patient_name", length=100)
    private String patientName;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="gender", length=10)
    private String gender;

    @Column(name="contact_number", length=15)
    private String contactNumber;

    @Column(name="address", length=255)
    private String address;

    @Column(name="medical_history", columnDefinition="TEXT")
    private String medicalHistory;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id", unique=true, nullable=false)
    private User user;
    
    @OneToMany(mappedBy="patient",cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    
    @OneToMany(mappedBy="patient")
    private List<Bill> bills;
    
    
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String name) {
        this.patientName = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public String getAddress() {
       return address;
    }
    public void setAddress(String address) {
    	this.address = address;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	

//	@Override
//    public String toString() {
//        return "Patient [patientId=" + patientId + ", name=" + patientName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
//                + ", contactNumber=" + contactNumber + ", address=" + address + ", medicalHistory=" + medicalHistory + ", user=" + user + "]";
//    }
}