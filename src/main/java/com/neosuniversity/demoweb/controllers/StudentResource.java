package com.neosuniversity.demoweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.neosuniversity.demoweb.domain.Student;

@RestController
public class StudentResource {


	@GetMapping("/")
	public String saludo() {
		return "Hola MUNDO Spring Boot";
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
