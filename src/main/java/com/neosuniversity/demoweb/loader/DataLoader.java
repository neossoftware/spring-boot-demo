package com.neosuniversity.demoweb.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.neosuniversity.demoweb.domain.Student;
import com.neosuniversity.demoweb.jpa.StudentRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private StudentRepository studentRepo;

    @Autowired
    public DataLoader(StudentRepository userRepository) {
        this.studentRepo = userRepository;
    }

    public void run(ApplicationArguments args) {
        	studentRepo.save(new Student(new Long(1), "Mario Hidalgo" ,"1"));
        	studentRepo.save(new Student(new Long(2), "Mario Hidalgo" ,"2"));
        	studentRepo.save(new Student(new Long(3), "Mario Hidalgo" ,"3"));
    }
}
