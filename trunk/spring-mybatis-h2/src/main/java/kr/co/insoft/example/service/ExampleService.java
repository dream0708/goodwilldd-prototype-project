package kr.co.insoft.example.service;

import java.util.List;

import kr.co.insoft.example.entity.Example;
import kr.co.insoft.example.mapper.NExampleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Cacheable(value="exampleService")
public class ExampleService {
	@Autowired
	NExampleMapper nExampleMapper;

	public List<Example> getExamList2() {
		return nExampleMapper.getList();
	}

	@Transactional(readOnly = true)
	public Example getExam2(long seq) {
		return nExampleMapper.getExam(seq);
	}

	public void doInsertExample(Example exam) {
		nExampleMapper.save(exam);
	}

}
