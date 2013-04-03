package kr.co.ujin.service.main.service;

import java.util.List;

import javax.annotation.Resource;

import kr.co.ujin.core.util.CommonPropertiesUtil;
import kr.co.ujin.service.main.entity.Customer;
import kr.co.ujin.service.main.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

}
