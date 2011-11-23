package com.mycompany.validators;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mycompany.model.Customer;
import com.mycompany.model.CustomerAddress;


@Component
public class CustomerValidator {

    /**
     * Spring Web Flow activated validation (validate + ${state}).
     * Validates 'personForm' view state after binding to person.
     */
    public void validateCustomerForm(Customer customer, MessageContext context) {
        if (!StringUtils.hasText(customer.getFirstname())) {
            context.addMessage(new MessageBuilder().error().source("firstname").code("customer.form.firstName.error").build());
        }

        if (!StringUtils.hasText(customer.getLastname())) {
            context.addMessage(new MessageBuilder().error().source("lastname").code("customer.form.lastName.error").build());
        }
    }
    
    public void validateAddressForm(CustomerAddress address, MessageContext context) {
        if (!StringUtils.hasText(address.getStreet())) {
            context.addMessage(new MessageBuilder().error().source("street").code("address.form.street.error").build());
        }

        if (!StringUtils.hasText(address.getCity())) {
            context.addMessage(new MessageBuilder().error().source("city").code("address.form.city.error").build());
        }
        
        if (!StringUtils.hasText(address.getState())) {
            context.addMessage(new MessageBuilder().error().source("state").code("address.form.state.error").build());
        }
        
        if (!StringUtils.hasText(address.getZip())) {
            context.addMessage(new MessageBuilder().error().source("zip").code("address.form.zip.error").build());
        }
        
        if (!StringUtils.hasText(address.getCountry())) {
            context.addMessage(new MessageBuilder().error().source("country").code("address.form.country.error").build());
        }
    }
    
}