package com.soch.uam.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "questionnaire_tb")
public class QuestionaireEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6304386147360069806L;


	@Id
	@Column(name = "questionId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator="QASeqGen")
	 @TableGenerator(
		        name="QASeqGen", 
		        table="seq_tb", 
		        pkColumnName="seq_name", 
		        valueColumnName="seq_val", 
		        pkColumnValue="questionnaire_tb.questionId", 
		        allocationSize=1)
	private String questionId;
	
	
	@Column(name = "questionDescription")
	private String questionDescription;
	
	@OneToMany(mappedBy="questionaireEntity",cascade = CascadeType.ALL,targetEntity=SecurityQAEntity.class) 
	@Fetch(value = FetchMode.SELECT)
	@JsonManagedReference
	private Set<SecurityQAEntity> securityQA;
	
	


	public Set<SecurityQAEntity> getSecurityQA() {
		return securityQA;
	}


	public void setSecurityQA(Set<SecurityQAEntity> securityQA) {
		this.securityQA = securityQA;
	}


	public String getQuestionId() {
		return questionId;
	}


	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}


	public String getQuestionDescription() {
		return questionDescription;
	}


	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	
	


}
