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
@Table(name="CUSTOMER_SECURITY_QUESTION")
public class CustomerSecurityQuestion extends BaseObject implements Serializable {

	
	private static final long serialVersionUID = -4244148893199431307L;
	
	private Long id;
	private Long customerId;
	private String question;
	private String answer;
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO,generator="CUSTOMER_SECURITY_QUESTION_SEQ")
    @SequenceGenerator(name="CUSTOMER_SECURITY_QUESTION_SEQ", sequenceName="CUSTOMER_SECURITY_QUESTION_SEQ")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	

	@Column(name="QUESTION")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(name="ANSWER")
	public String getAnswer() {
		return answer;
	}
	

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
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
		CustomerSecurityQuestion other = (CustomerSecurityQuestion) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
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
		return "CustomerSecurityQuestion [id=" + id + ", customerId="
				+ customerId + ", question=" + question + ", answer=" + answer
				+ "]";
	}

	
	

	

	

}
