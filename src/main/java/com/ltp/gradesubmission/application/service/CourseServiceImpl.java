package com.ltp.gradesubmission.application.service;

import java.util.List;
import java.util.Optional;

import com.ltp.gradesubmission.application.exception.CourseNotFoundException;
import com.ltp.gradesubmission.application.repository.CourseRepository;
import com.ltp.gradesubmission.domain.entity.Course;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    
    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {  
        courseRepository.deleteById(id);      
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>)courseRepository.findAll();
    }
 
    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CourseNotFoundException(id);
    }


}
