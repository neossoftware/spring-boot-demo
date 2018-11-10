package com.neosuniversity.demoweb.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosuniversity.demoweb.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
