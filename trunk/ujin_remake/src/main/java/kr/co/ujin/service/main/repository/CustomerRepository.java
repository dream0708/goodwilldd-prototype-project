package kr.co.ujin.service.main.repository;

import kr.co.ujin.service.main.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
