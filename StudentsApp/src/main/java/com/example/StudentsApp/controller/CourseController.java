package com.example.StudentsApp.controller;

import com.example.StudentsApp.model.Course;
import com.example.StudentsApp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //localhost:8080/courses/createCourse
    @PostMapping("/createCourse")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    //localhost:8080/courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
