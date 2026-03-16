package org.example.tranduytan.controller;

import org.example.tranduytan.entity.Student;
import org.example.tranduytan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Xử lý logic lưu sinh viên
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        // 1. Mã hóa mật khẩu trước khi lưu (BẮT BUỘC)
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        // 2. Gán quyền mặc định
        student.setRole("ROLE_STUDENT");

        // 3. Lưu vào database
        studentRepository.save(student);

        return "redirect:/login"; // Chuyển hướng về trang đăng nhập
    }

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}