package kr.co.insoft.core.service;

import kr.co.insoft.core.mapper.normal.CommonNormalMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 공통으로 사용하는 모든 서비스를 총괄한다.
 * </pre>
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
@Service
public class CommonService {
	@Autowired
	CommonNormalMapper mapper;
}
