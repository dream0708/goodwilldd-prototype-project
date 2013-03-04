package kr.co.insoft.mybatis.example.service;

import kr.co.insoft.mybatis.example.normal.mapper.ExampleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService implements ExampleServiceIF {

	@Autowired
	private ExampleMapper exampleMapper;

	@Override
	public String getUserName(String user_id) {
		return exampleMapper.getUserName(user_id);
	}
}
