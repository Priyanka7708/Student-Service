package com.priyankak.springboot.controller;

import com.priyankak.springboot.model.Course;
import com.priyankak.springboot.model.Student;
import com.priyankak.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{studentId}/courses")
    public List<Course> getCoursesForStudent(@PathVariable String studentId) {
        return studentService.getCourses(studentId);
    }

    @GetMapping("/students/")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable String studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/students/{studentId}/courses/{courseId}")
    public Course getCourseDetails(@PathVariable String studentId, @PathVariable String courseId) {
        return studentService.getCourse(studentId, courseId);
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Void> registerStudentForCourse(@PathVariable String studentId, @RequestBody Course newCourse) {
        Course course = studentService.addCourse(studentId, newCourse);
        if (course == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}

