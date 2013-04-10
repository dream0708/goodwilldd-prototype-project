package kr.co.insoft.example.service;

import java.util.List;

import kr.co.insoft.example.entity.Example;
import kr.co.insoft.example.mapper.batch.BExampleMapper;
import kr.co.insoft.example.mapper.normal.NExampleMapper;
import kr.co.insoft.example.mapper.repository.ExampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExampleService {
	@Autowired
	BExampleMapper bExampleMapper;

	@Autowired
	NExampleMapper nExampleMapper;

	@Autowired
	ExampleRepository exampleRepository;

	@Transactional(readOnly = true)
	public Example getExam1(long seq) {
		return exampleRepository.findOne(seq);
	}
	
	public List<Example> getExamList() {
		return exampleRepository.findAll();
	}
	
	public List<Example> getExamList2() {
		return nExampleMapper.getList();
	}

	@Transactional(readOnly = true)
	public Example getExam2(long seq) {
		return nExampleMapper.getExam(seq);
	}

	public void doInsertExampleJPA(Example exam) {
		exampleRepository.saveAndFlush(exam);
	}

	public void doInsertExample(Example exam) {
		nExampleMapper.save(exam);
	}

}
