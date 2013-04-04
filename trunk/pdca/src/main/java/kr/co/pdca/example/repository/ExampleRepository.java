package kr.co.pdca.example.repository;

import kr.co.pdca.example.entity.Example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

}
