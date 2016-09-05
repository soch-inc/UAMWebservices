package com.soch.uam.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "USER_TB")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6852388174029492331L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator="userSeqGen")
	 @TableGenerator(
		        name="userSeqGen", 
		        table="seq_tb", 
		        pkColumnName="seq_name", 
		        valueColumnName="seq_val", 
		        pkColumnValue="USER_TB.ID", 
		        allocationSize=1)
	private Integer id;

	@NotNull
	@Column(name = "USER_ID")
	private String userId;

	@NotNull
	@Column(name = "password")
	private String password;

	@Column(name = "ssn")
	private String SSN;

	@Column(name = "phone_num")
	private String phoneNumber;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "middleName")
	private String middleName;

	@Column(name = "emailId")
	private String emailId;

	@Column(name = "activeFlag", columnDefinition = "dafault false")
	private boolean activeFlag;

	@Column(name = "lockFlag" , columnDefinition = "dafault false")
	private boolean lockFlag;

	@Column(name = "createdTs")
	private Date createdTs;

	@Column(name = "updatedTs")
	private Date updatedTs;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "updatedBy")
	private String updatedBy;
	
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;
	
	@Column(name = "loginFailureCount")
	private int loginFailureCount;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<AddressEntity> address;
	
		
	@OneToMany(mappedBy="userEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	private Set<SecurityQAEntity> securityQA;
	
	@OneToMany(mappedBy="userEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@OrderBy("loginTs DESC")
	private Set<LoginEntity> logintEntity;
	
	
	
	public int getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(int loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Set<LoginEntity> getLogintEntity() {
		return logintEntity;
	}

	public void setLogintEntity(Set<LoginEntity> logintEntity) {
		this.logintEntity = logintEntity;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassowrd() {
		return password;
	}

	public void setPassowrd(String password) {
		this.password = password;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public boolean getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(boolean lockFlag) {
		this.lockFlag = lockFlag;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<AddressEntity> getAddress() {
		return address;
	}

	public void setAddress(Set<AddressEntity> address) {
		this.address = address;
	}

	public Set<SecurityQAEntity> getSecurityQA() {
		return securityQA;
	}

	public void setSecurityQA(Set<SecurityQAEntity> securityQA) {
		this.securityQA = securityQA;
	}
	
	

}
