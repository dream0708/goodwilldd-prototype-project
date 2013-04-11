package kr.co.insoft.example.service;

import java.util.List;

import kr.co.insoft.example.entity.Example;
import kr.co.insoft.example.mapper.batch.BExampleMapper;
import kr.co.insoft.example.mapper.normal.NExampleMapper;

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
