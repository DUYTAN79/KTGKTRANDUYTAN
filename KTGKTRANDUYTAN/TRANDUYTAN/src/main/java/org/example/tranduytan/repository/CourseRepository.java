package org.example.tranduytan.repository;

import org.example.tranduytan.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Tìm kiếm theo tên (Câu 8) và phân trang (Câu 1)
    Page<Course> findByNameContaining(String name, Pageable pageable);
}