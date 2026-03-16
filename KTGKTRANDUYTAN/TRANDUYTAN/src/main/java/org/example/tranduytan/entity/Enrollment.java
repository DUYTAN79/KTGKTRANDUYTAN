package org.example.tranduytan.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data // Đảm bảo đã có cái này để Lombok tự tạo setter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    // THÊM DÒNG NÀY VÀO
    private LocalDate enrollmentDate;
}