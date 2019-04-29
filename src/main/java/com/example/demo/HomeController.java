package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitys.Employee;
import com.example.demo.repository.EmpRepository;

@RestController
@RequestMapping("/emp")
public class HomeController {

	@Autowired
	private EmpRepository empRepository;

	/*
	 * Get all Details of Employee
	 */
	@GetMapping(value = "/all")
	public List<Employee> findAll() {
		List<Employee> findAll = empRepository.findAll();
		System.err.println(findAll);
		return empRepository.findAll();
	}

	/*
	 * Find by Place
	 */
	@GetMapping(value = "/{place}")
	public List<Employee> findByPlace(@PathVariable final String place) {
		return empRepository.findByPlace(place);
	}

	/*
	 * Find by Name
	 */
	@GetMapping(value = "/user/{name}")
	public ResponseEntity<List<Employee>> findByName(@PathVariable final String name) {
		try{
			List<Employee> empList = empRepository.findByName(name);
			return ResponseEntity.ok(empList);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	/*
	 * Update the Employee Details...
	 */
	@PostMapping(value = "/Update")
	public ResponseEntity<Employee> UpdateEmp(@RequestBody final Employee emp) {

		Integer id = emp.getId();
		System.err.println("ID: "+id);
		Optional<Employee> byId = empRepository.findById(id);
		if (!byId.isPresent()) {
			empRepository.save(emp);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emp);
		} else {
			empRepository.deleteById(id);
			empRepository.save(emp);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emp);

	}
	/*
	 * Store Employee Details.
	 */
	@PostMapping(value = "/load")
	public List<Employee> load(@RequestBody final Employee employee) {
		empRepository.save(employee);
		return empRepository.findByName(employee.getName());
	}

	/*
	 * Delete Employee Detils.
	 */
	@DeleteMapping(value = "/del/{id}")
	public ResponseEntity<Employee> deleteEmp(@PathVariable final Integer id) {
		Employee emp = null;
		HttpHeaders responseHeaders = null;
		try {
			emp = empRepository.getOne(id);
			empRepository.deleteById(id);
			return ResponseEntity.ok(emp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
