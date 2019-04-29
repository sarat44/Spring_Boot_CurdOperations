package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entitys.Employee;

@Component
public interface EmpRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByName(final String name);
	
	List<Employee> findByPlace(final String place);
//	@Query("delete from Employee e where e.id= ?1")
//	Employee deleteByOne(Integer id);
}
