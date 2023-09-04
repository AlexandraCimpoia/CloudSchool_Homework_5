package com.example.StudentsApp.controller;

import com.example.StudentsApp.model.Enrollment;
import com.example.StudentsApp.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/createEnrollment")
    public Enrollment enrollStudentInCourse(@RequestBody Enrollment enrollment) {
        return enrollmentService.enrollStudentInCourse(enrollment);
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsForStudent(@PathVariable Integer studentId) {
        return enrollmentService.getEnrollmentsForStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsForCourse(@PathVariable Integer courseId) {
        return enrollmentService.getEnrollmentsForCourse(courseId);
    }
}
