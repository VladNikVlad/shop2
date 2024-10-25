package com.vladyslavnicko.gmail.DTO;

import com.vladyslavnicko.gmail.model.Address;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressDTO {

	private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    
    public static AddressDTO fromAddress(Address o) {
    	AddressDTO a = new AddressDTO();
    	a.setId(o.getId());
    	a.setStreet(o.getStreet());
    	a.setCity(o.getCity());
    	a.setState(o.getState());
    	a.setZip(o.getZip());
    	
    	return a;
    }
    
    public static Address toAddress(AddressDTO o) {
    	Address a = new Address();
    	a.setId(o.getId());
    	a.setStreet(o.getStreet());
    	a.setCity(o.getCity());
    	a.setState(o.getState());
    	a.setZip(o.getZip());
    	
    	return a;
    }
}
