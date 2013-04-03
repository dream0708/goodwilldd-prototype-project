package kr.co.ujin.service.main.service;

import java.util.List;

import javax.annotation.Resource;

import kr.co.ujin.core.config.CommonConstance;
import kr.co.ujin.core.util.CommonPropertiesUtil;
import kr.co.ujin.service.main.entity.Finance;
import kr.co.ujin.service.main.repository.FinanceRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FinanceServiceImpl implements FinanceService {

	private static final Log logger = LogFactory
			.getLog(FinanceServiceImpl.class);

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	@Autowired
	FinanceRepository financeRepository;

	@Override
	public List<Finance> findAll() {
		return financeRepository.findAll();
	}

	@Override
	public Page<Finance> findAll(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1,
				commonPropertiesUtil.getInt(CommonConstance.PAGE_SIZE), Sort.Direction.DESC, "seq");
		return financeRepository.findAll(request);
	}

}
