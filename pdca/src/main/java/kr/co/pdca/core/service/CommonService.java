package kr.co.pdca.core.service;

import kr.co.pdca.core.mapper.normal.CommonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 공통으로 사용하는 모든 서비스를 총괄한다.
 * 
 * @author kr.goodwilldd@gmail.com
 * 
 */
@Service
public class CommonService {
	@Autowired
	CommonMapper mapper;
}
