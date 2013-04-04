package kr.co.pdca.example.service;

import java.util.List;

import kr.co.pdca.example.entity.Example;

public interface ExampleService {
	public List<Example> findAll();
	
	public List<Example> findAllByMyBatis();
}
