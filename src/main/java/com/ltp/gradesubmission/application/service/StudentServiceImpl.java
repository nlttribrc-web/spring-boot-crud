package com.ltp.gradesubmission.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.application.exception.StudentNotFoundException;
import com.ltp.gradesubmission.application.repository.StudentRepository;
import com.ltp.gradesubmission.domain.entity.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {


    StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student, id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {      
        studentRepository.deleteById(id);  
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>)studentRepository.findAll();
    }
    
    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new StudentNotFoundException(id);
    }


}
