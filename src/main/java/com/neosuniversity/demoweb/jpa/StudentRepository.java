package com.neosuniversity.demoweb.jpa;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.neosuniversity.demoweb.domain.Student;

import java.util.List;

/**
 * CRUD Operations <br>
 * for student
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
    List<Student> findAllByOrderByIdAsc();
}
