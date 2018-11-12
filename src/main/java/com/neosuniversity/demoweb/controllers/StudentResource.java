package com.neosuniversity.demoweb.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.neosuniversity.demoweb.business.StudentIBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neosuniversity.demoweb.domain.Student;
import com.neosuniversity.demoweb.domain.StudentNotFoundException;
import com.neosuniversity.demoweb.jpa.StudentRepository;

@RestController
public class StudentResource {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	@Qualifier("studenBusiness")
	private StudentIBusiness studentIBusiness;

	@GetMapping("/helloworld")
	public String saludo() {
		return "Hola MUNDO Spring Boot";
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody  Student student) {
		
		return student;
	}

	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
        return studentIBusiness.findAllStudents();
	}

	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		return studentIBusiness.findStudentById(id);
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
        studentIBusiness.deleteStudentById(id);
	}

	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}

}
