package org.example.tranduytan.controller;

import org.example.tranduytan.entity.Course;
import org.example.tranduytan.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    @Autowired private CourseRepository courseRepo;

    @GetMapping({"/", "/home"})
    public String listCourses(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(required = false) String keyword, Model model) {
        Pageable pageable = PageRequest.of(page, 5); // Phân trang 5 phần tử/trang
        Page<Course> coursePage = (keyword == null || keyword.isEmpty())
                ? courseRepo.findAll(pageable)
                : courseRepo.findByNameContaining(keyword, pageable);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("keyword", keyword);
        return "home";
    }
}