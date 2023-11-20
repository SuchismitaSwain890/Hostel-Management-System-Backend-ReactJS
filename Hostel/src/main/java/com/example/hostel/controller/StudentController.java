package com.example.hostel.controller;

import com.example.hostel.model.Student;
import com.example.hostel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/getAllStudents") // Corrected endpoint mapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
        }
    }


    @GetMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }

    @PostMapping("/editStudent/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> existingStudent = studentService.getStudentById(id);
        if (existingStudent.isPresent()) {
            student.setId(id);
            Student updatedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
