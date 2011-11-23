package com.mycompany.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;

@Entity
@Table(name="CUSTOMER_ADDRESS")

public class CustomerAddress extends BaseObject implements Serializable {

	
	private static final long serialVersionUID = -5379555431076434018L;
	
	private Long id;
	private Long customerId;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO,generator="CUSTOMER_ADDRESS_SEQ")
    @SequenceGenerator(name="CUSTOMER_ADDRESS_SEQ", sequenceName="CUSTOMER_ADDRESS_SEQ")
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

	@Column(name="STREET")
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="ZIP")
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void validateAddressForm(MessageContext context) {
	    if (!StringUtils.hasText(street)) {
	        context.addMessage(new MessageBuilder().error().source("street").code("address.form.street.error").build());
	    }
	    
	    if (!StringUtils.hasText(city)) {
	        context.addMessage(new MessageBuilder().error().source("city").code("address.form.city.error").build());
	    }
	    
	    if (!StringUtils.hasText(state)) {
	        context.addMessage(new MessageBuilder().error().source("state").code("address.form.state.error").build());
	    }
	    
	    if (!StringUtils.hasText(zip)) {
	        context.addMessage(new MessageBuilder().error().source("zip").code("address.form.zipPostal.error").build());
	    }
	    
	    if (!StringUtils.hasText(country)) {
	        context.addMessage(new MessageBuilder().error().source("country").code("address.form.country.error").build());
	    }
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CustomerAddress other = (CustomerAddress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "CustomerAddress [id=" + id + ", customerId=" + customerId + ", street="
				+ street + ", city=" + city + ", state=" + state + ", zip="
				+ zip + ", country=" + country + "]";
	}
	
	
	
	

}
