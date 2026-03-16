package org.example.tranduytan.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int credits;

    // Đảm bảo Database có các cột này
    private String teacherName;
    private String imageUrl;
}