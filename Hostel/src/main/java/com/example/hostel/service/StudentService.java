package com.example.hostel.service;

import com.example.hostel.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    String deleteStudent(Long id);
}
