package org.example.tranduytan.controller;

import org.example.tranduytan.entity.Course;
import org.example.tranduytan.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired private CourseRepository courseRepo;

    @GetMapping("/courses")
    public String list(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "admin/course-list";
    }

    @PostMapping("/courses/save")
    public String save(@ModelAttribute Course course) {
        courseRepo.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String delete(@PathVariable Long id) {
        courseRepo.deleteById(id);
        return "redirect:/admin/courses";
    }
}