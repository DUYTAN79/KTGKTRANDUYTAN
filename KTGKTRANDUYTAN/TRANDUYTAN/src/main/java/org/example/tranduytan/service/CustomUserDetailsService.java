package org.example.tranduytan.service;

import org.example.tranduytan.entity.Student;
import org.example.tranduytan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if (student == null) throw new UsernameNotFoundException("Không tìm thấy sinh viên!");

        return User.withUsername(student.getUsername())
                .password(student.getPassword()) // Mật khẩu đã mã hóa
                .authorities(student.getRole())  // Role khớp với DB
                .build();
    }
}