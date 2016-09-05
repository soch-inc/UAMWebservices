package com.soch.uam.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "address_tb")
public class AddressEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4328782071453335533L;

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator="addressSeqGen")
	 @TableGenerator(
		        name="addressSeqGen", 
		        table="seq_tb", 
		        pkColumnName="seq_name", 
		        valueColumnName="seq_val", 
		        pkColumnValue="ADDRESS_TB.ADDRESS_ID", 
		        allocationSize=1)
	private int address_id;

	@Column(name = "addressLine1")
	private String addressLine1;

	@Column(name = "addressLine2")
	private String addressLine2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zipCode")
	private String zipcode;

	@Column(name = "createdTs")
	private Date createdTs;

	@Column(name = "updatedTs")
	private Date updatedTs;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "updatedBy")
	private String updatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "ID")
	private UserEntity userEntity;

	

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
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

}
