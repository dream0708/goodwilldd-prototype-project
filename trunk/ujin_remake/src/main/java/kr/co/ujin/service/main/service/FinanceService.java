package kr.co.ujin.service.main.service;

import java.util.List;

import kr.co.ujin.service.main.entity.Finance;

import org.springframework.data.domain.Page;

public interface FinanceService {
	public List<Finance> findAll();

	public Page<Finance> findAll(int pageNumber);
}
