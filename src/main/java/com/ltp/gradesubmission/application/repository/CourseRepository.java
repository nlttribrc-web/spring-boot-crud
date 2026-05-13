package com.ltp.gradesubmission.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.ltp.gradesubmission.domain.entity.Course;


public interface CourseRepository extends CrudRepository<Course, Long> {

}