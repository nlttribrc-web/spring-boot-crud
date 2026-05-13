package com.ltp.gradesubmission.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.ltp.gradesubmission.domain.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}