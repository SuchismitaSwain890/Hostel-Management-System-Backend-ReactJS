package com.example.hostel.service;

import com.example.hostel.model.Student;
import com.example.hostel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public String deleteStudent(Long id) {
        Student student=studentRepository.findById(id).get();
        if (student != null) {
            studentRepository.delete(student);
            return "Student Deleted Successfully";
        }
        return "Student not found or Something Went Wrong";
    }
}
