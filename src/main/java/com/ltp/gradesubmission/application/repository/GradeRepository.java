package com.ltp.gradesubmission.application.repository;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.gradesubmission.domain.entity.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
     Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);
     List<Grade> findByStudentId(Long studentId);
     List<Grade> findByCourseId(Long courseId);
     @Transactional
     void deleteByStudentIdAndCourseId(Long studentId, Long courseId);

}