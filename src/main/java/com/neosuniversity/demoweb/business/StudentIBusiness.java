package com.neosuniversity.demoweb.business;

import com.neosuniversity.demoweb.domain.Student;

import java.util.List;

/**
 *  Business Operations <br>
 * for student
 */
public interface StudentIBusiness {
    /**
     * find All students on Data Base
     * @return List of Studentes
     */
    List<Student> findAllStudents();

    /**
     * find  student by id on Data Base
     * @param id
     * @return Student
     */
    Student findStudentById(long id);

    /**
     * delete  student by id on Data Base
     * @param id
     */
    void deleteStudentById(long id);

    /**
     *  save  a student on Data Base
     * @param student
     * @return
     */
    Student saveStudent(Student student);

    /**
     *  update student on Data Base
     * @param student
     * @param id
     * @return
     */
    Student updateStudent(Student student, long id);

}
