package com.neosuniversity.demoweb.dao;

import com.neosuniversity.demoweb.domain.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getAllStudents();

    Student getStudentById(long studentId);

    Student addNewStudent(Student student);

    Student updateStudent(Student student,long studentId);

    void deleteStudentById(long studentId);

}
