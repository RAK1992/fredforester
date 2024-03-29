package com.mycompany.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SECURITY_QUESTION")
public class SecurityQuestion extends BaseObject implements Serializable {

	private static final long serialVersionUID = -3145827041870379156L;
	
	private Long id;
	private String question;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO,generator="SECURITY_QUESTION_SEQ")
    @SequenceGenerator(name="SECURITY_QUESTION_SEQ", sequenceName="SECURITY_QUESTION_SEQ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="QUESTION")
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecurityQuestion other = (SecurityQuestion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SecurityQuestions [id=" + id + ", question=" + question + "]";
	}
	
	
	
	
	
	
	
	

}
