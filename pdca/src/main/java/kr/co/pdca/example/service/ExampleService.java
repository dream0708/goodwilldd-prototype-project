package kr.co.pdca.example.service;

import java.util.List;

import javax.annotation.Resource;

import kr.co.pdca.core.util.CommonPropertiesUtil;
import kr.co.pdca.example.entity.Example;
import kr.co.pdca.example.mapper.normal.ExampleMapper;
import kr.co.pdca.example.repository.ExampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExampleService {

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	@Autowired
	ExampleMapper exampleMapper;

	@Resource
	ExampleRepository exampleRepository;

	public List<Example> findAll() {
		return exampleRepository.findAll();
	}

	public List<Example> findAllByMyBatis() {
		return exampleMapper.getExampleList();
	}

}
