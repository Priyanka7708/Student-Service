package com.priyankak.springboot.service;

import com.priyankak.springboot.model.Course;
import com.priyankak.springboot.model.Student;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentService {
    private static List<Student> students = new ArrayList<>();

    static {
        Course course1 = new Course("Course1", "Spring", "Learn Spring");
        Course course2 = new Course("Course2", "Spring Boot", "Learn Spring Boot");
        Course course3 = new Course("Course3", "Smpring MVC", "Learn MVC");

        Student student1 = new Student("Student1", "Priyanka", "Singer, Dancer, Programmer", new ArrayList<>(Arrays.asList(course1, course2, course3)));
        Student student2 = new Student("Student2", "Priya", "Writer, Dancer and Chef", new ArrayList<>(Arrays.asList(course1, course2)));

        students.add(student1);
        students.add(student2);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public List<Course> getCourses(String studentId) {
        Student student = getStudent(studentId);
        if (student == null)
            return null;

        return student.getCourses();
    }

    public Course getCourse(String studentId, String courseId) {
        Student student = getStudent(studentId);

        if (student == null)
            return null;

        for (Course course : student.getCourses()) {
            if (course.getId().equals(courseId))
                return course;
        }

        return null;
    }

    private SecureRandom random = new SecureRandom();

    public Course addCourse(String studentId, Course course) {
        Student student = getStudent(studentId);
        if (student == null)
            return null;

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }
}
