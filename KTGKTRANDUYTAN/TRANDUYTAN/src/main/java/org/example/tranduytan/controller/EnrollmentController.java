package org.example.tranduytan.controller;

import org.example.tranduytan.entity.*;
import org.example.tranduytan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;

@Controller
public class EnrollmentController {
    @Autowired private EnrollmentRepository enrollRepo;
    @Autowired private StudentRepository studentRepo;
    @Autowired private CourseRepository courseRepo;

    // Xem danh sách đã đăng ký
    @GetMapping("/my-courses")
    public String viewMyCourses(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";

        Student student = studentRepo.findByUsername(principal.getName());
        model.addAttribute("enrollments", enrollRepo.findByStudent(student));
        return "student/my-courses";
    }

    // Xử lý nút Đăng ký
    @PostMapping("/enroll/{courseId}")
    public String enrollCourse(@PathVariable Long courseId, Principal principal) {
        if (principal == null) return "redirect:/login";

        Student student = studentRepo.findByUsername(principal.getName());
        Course course = courseRepo.findById(courseId).orElseThrow();

        if (enrollRepo.findByStudentAndCourse(student, course) == null) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrollmentDate(LocalDate.now());
            enrollRepo.save(enrollment);
        }
        return "redirect:/my-courses";
    }
}