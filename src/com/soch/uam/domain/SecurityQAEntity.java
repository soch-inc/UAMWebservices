package com.soch.uam.domain;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "securityqa_tb")
public class SecurityQAEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2066874514380885761L;

	@Id
	@Column(name = "securityQAid")
	@GeneratedValue(strategy = GenerationType.TABLE, generator="QASeqGen")
	 @TableGenerator(
		        name="QASeqGen", 
		        table="seq_tb", 
		        pkColumnName="seq_name", 
		        valueColumnName="seq_val", 
		        pkColumnValue="questionnaire_tb.questionId", 
		        allocationSize=1)
	private int securityQAid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "ID")
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "questionId", insertable = true, updatable = true, nullable = false)
	private QuestionaireEntity questionaireEntity;
	
	
	
	@Column(name = "qDescription")
	private String question;
	
	
	@Column(name = "answer")
	private String answer;

	@Column(name = "createdTs")
	private Date createdTs;

	@Column(name = "updatedTS")
	private Date updatedTS;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "updatedBy")
	private String updatedBy;

	public int getsecurityQAid() {
		return securityQAid;
	}

	public void setsecurityQAid(int securityQAid) {
		this.securityQAid = securityQAid;
	}

	public UserEntity getUser() {
		return userEntity;
	}

	public void setUser(UserEntity user) {
		this.userEntity = user;
	}

	

	public int getSecurityQAid() {
		return securityQAid;
	}

	public void setSecurityQAid(int securityQAid) {
		this.securityQAid = securityQAid;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public QuestionaireEntity getQuestionaireEntity() {
		return questionaireEntity;
	}

	public void setQuestionaireEntity(QuestionaireEntity questionaireEntity) {
		this.questionaireEntity = questionaireEntity;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	

	public Date getUpdatedTS() {
		return updatedTS;
	}

	public void setUpdatedTS(Date updatedTS) {
		this.updatedTS = updatedTS;
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
	
}
