package com.neosuniversity.demoweb.business;

import com.neosuniversity.demoweb.domain.Student;

import java.util.List;

public interface StudentIBusiness {

    List<Student> findAllStudents();
    Student findStudentById(long id);
    void deleteStudentById(long id);

}
