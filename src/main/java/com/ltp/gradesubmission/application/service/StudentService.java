package com.ltp.gradesubmission.application.service;

import java.util.List;

import com.ltp.gradesubmission.domain.entity.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
}