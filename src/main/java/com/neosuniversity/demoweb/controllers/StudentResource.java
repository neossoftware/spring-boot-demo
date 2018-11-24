package com.neosuniversity.demoweb.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.neosuniversity.demoweb.business.StudentIBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.neosuniversity.demoweb.jpa.StudentRepository;

import javax.validation.Valid;

/**
 * ALL Rest Service Operations <br>
 * for student
 */
@RestController
@Api(value="students resources", description="Operations for students")
public class StudentResource {
    Logger logger = LoggerFactory.getLogger(StudentResource.class);

	@Autowired
	@Qualifier("studentBusiness")
	private StudentIBusiness studentBusiness;

	@GetMapping("/healthcheck/students")
	public String healthcheck() {
		return "::The StudentResource are working!!::";
	}

	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
        logger.debug(":::searching all students:::");
        return studentBusiness.findAllStudents();
	}

    @ApiOperation(value = "get students by Id")
	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable("id") long id) {
        logger.debug(":::searching student id= {} :::",id);
        Student retorno = studentBusiness.findStudentById(id);
		return retorno;
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable("id") long id) {
        logger.debug(":::Deleting student id= {} :::",id);
		studentBusiness.deleteStudentById(id);
	}

	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody(required = false) Student student) {
        logger.debug(":::creating student :::");
        Student savedStudent = studentBusiness.saveStudent(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody(required = false) Student student, @PathVariable("id") long id) {
        logger.debug(":::updating student id= {} :::",id);
        Optional<Student> studentOptional = Optional.ofNullable(studentBusiness.updateStudent(student,id));

        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.noContent().build();
        }
	}
}
