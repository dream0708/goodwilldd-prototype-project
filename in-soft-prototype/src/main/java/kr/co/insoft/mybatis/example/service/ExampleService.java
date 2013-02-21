package kr.co.insoft.mybatis.example.service;

import kr.co.insoft.mybatis.example.mapper.ExampleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService implements ExampleServiceIF {

	private ExampleMapper exampleMapper;

	@Autowired
	public ExampleService(ExampleMapper exampleMapper) {
		this.exampleMapper = exampleMapper;
	}

	@Override
	public String getUserName(String user_id) {
		return exampleMapper.getUserName(user_id);
	}
}
