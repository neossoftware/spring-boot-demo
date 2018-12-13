package com.neosuniversity.demoweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosuniversity.demoweb.business.GoogleServices;
import com.neosuniversity.demoweb.domain.Student;

@RestController
public class StudentResource {

	private GoogleServices googleServices;

	public StudentResource(GoogleServices googleServices) {
		this.googleServices = googleServices;
	}

	@GetMapping("/")
	public String saludo() {
		return "Hola MUNDO Spring Boot";
	}

	// http://localhost:8080/book/1449374646
	@GetMapping("/book/{isbn}")
	public String saludo(@PathVariable("isbn") String isbn) {

		return googleServices.getDataBookGoogleCloud(isbn);
	}

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {

		return student;
	}

	@GetMapping("/allStudents")
	public List<Student> getAll() {

		List<Student> list = new ArrayList<Student>();

		Student a = new Student(new Long(12), "Mario", "232332");
		Student b = new Student(new Long(234), "Jorge", "433");
		Student c = new Student(new Long(2323), "Laura", "4334");

		list.add(a);
		list.add(b);
		list.add(c);

		return list;

	}

}
