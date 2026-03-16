package org.example.tranduytan.repository;

import org.example.tranduytan.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    Enrollment findByStudentAndCourse(Student student, Course course);
}