package com.neosuniversity.demoweb.dao;

import com.neosuniversity.demoweb.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Student> getAllStudents() {
        return mongoTemplate.findAll(Student.class);
    }

    @Override
    public Student getStudentById(long studentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(studentId));

        return  mongoTemplate.findOne(query, Student.class);
    }

    @Override
    public Student addNewStudent(Student student) {
       return  mongoTemplate.save(student);
    }

    @Override
    public Student updateStudent(Student student, long studentId) {

        return mongoTemplate.save(student);
    }

    @Override
    public void deleteStudentById(long studentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(studentId));
        mongoTemplate.remove(query, Student.class);
    }
}
