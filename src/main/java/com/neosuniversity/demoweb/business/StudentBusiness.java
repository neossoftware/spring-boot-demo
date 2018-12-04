package com.neosuniversity.demoweb.business;


import com.neosuniversity.demoweb.dao.StudentDao;
import com.neosuniversity.demoweb.domain.Student;
import com.neosuniversity.demoweb.domain.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business Operations <br>
 * for student
 */
@Service("studentBusiness")
public class StudentBusiness implements StudentIBusiness {


    private StudentDao studentDao;

    public StudentBusiness(StudentDao studentDao) {
        this.studentDao=studentDao;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public Student findStudentById(long id) {
        Optional<Student> student = Optional.ofNullable( studentDao.getStudentById(id)) ;

        if (!student.isPresent())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }

    @Override
    public void deleteStudentById(long id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentDao.addNewStudent(student);
    }

    @Override
    public Student updateStudent(Student student, long id) {
        Optional<Student> studentOptional =  Optional.ofNullable( studentDao.getStudentById(id)) ;
        Student studentResponse = null;
        if (!studentOptional.isPresent()) {
            return studentResponse = new Student();
        }
        student.setId(id);
        return studentDao.addNewStudent(student);
    }
}
