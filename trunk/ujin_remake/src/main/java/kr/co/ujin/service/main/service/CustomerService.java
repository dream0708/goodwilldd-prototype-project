package kr.co.ujin.service.main.service;

import java.util.List;

import kr.co.ujin.service.main.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();
	
	public void save(Customer customer);
}
