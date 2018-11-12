package com.neosuniversity.demoweb.business;

import com.neosuniversity.demoweb.domain.Student;
import com.neosuniversity.demoweb.domain.StudentNotFoundException;
import com.neosuniversity.demoweb.jpa.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business Operations <br>
 * for student
 */
@Service("studentBusiness")
public class StudentBusiness implements StudentIBusiness {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Student findStudentById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student studentResponse = null;
        if (!studentOptional.isPresent()) {
            return studentResponse = new Student();
        }
        student.setId(id);
        return studentRepository.save(student);
    }
}