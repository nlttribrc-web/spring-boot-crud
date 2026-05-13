package com.ltp.gradesubmission.application.service;

import java.util.List;

import com.ltp.gradesubmission.domain.entity.Course;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
}