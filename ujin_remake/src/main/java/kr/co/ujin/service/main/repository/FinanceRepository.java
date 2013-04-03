package kr.co.ujin.service.main.repository;

import kr.co.ujin.service.main.entity.Finance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
	
}
