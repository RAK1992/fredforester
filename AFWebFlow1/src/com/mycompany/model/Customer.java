package com.mycompany.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * A user who can book hotels.
 */
@Entity
@Table(name = "Customer")
    
public class Customer extends BaseObject implements Serializable {
	
	
	private static final long serialVersionUID = -6741517813943451305L;
	
	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String firstname;
	private String lastname;
	private Set<CustomerAddress> customerAddresses;
	
	private List<CustomerSecurityQuestion> questions;

	public Customer() {
	}

	/*
	public Customer(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	*/

	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO,generator="CUSTOMER_SEQ")
    @SequenceGenerator(name="CUSTOMER_SEQ", sequenceName="CUSTOMER_SEQ")
    public Long getId() {
        return id;
    }
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_ID")
	public List<CustomerSecurityQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<CustomerSecurityQuestion> questions) {
		this.questions = questions;
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_ID")
	public Set<CustomerAddress> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(Set<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}
	
	public CustomerAddress findAddressById(Long id) {
		CustomerAddress result = null;

        if (customerAddresses != null) {
            for (CustomerAddress address : customerAddresses) {
                if (address.getId().equals(id)) {
                    result = address;
                    break;
                }
            }
        }
        return result;
    }
	
	public CustomerSecurityQuestion findQuestionById(Long id) {
		CustomerSecurityQuestion result = null;

        if (questions != null) {
            for (CustomerSecurityQuestion q : questions) {
                if (q.getId().equals(id)) {
                    result = q;
                    break;
                }
            }
        }
        return result;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((customerAddresses == null) ? 0 : customerAddresses
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		//result = prime * result
		//		+ ((questions == null) ? 0 : questions.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Customer other = (Customer) obj;
		if (customerAddresses == null) {
			if (other.customerAddresses != null)
				return false;
		} else if (!customerAddresses.equals(other.customerAddresses))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		//if (questions == null) {
		//	if (other.questions != null)
		//		return false;
		//} else if (!questions.equals(other.questions))
		//	return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ret = "Customer [id=" + id + ", password="
				+ password + ", name=" + name + ", email=" + email
				+ ", firstname=" + firstname + ", lastname=" + lastname + "]";
		
		if (this.customerAddresses != null)
			ret += "," + this.customerAddresses;
		
		if (this.questions != null)
			ret += "," + this.questions;
		return ret;
		
	}

	
	
	
}
