package com.example.StudentsApp.tests;

import com.example.StudentsApp.StudentsAppApplication;
import com.example.StudentsApp.StudentsAppApplicationTests;
import com.example.StudentsApp.model.Course;
import com.example.StudentsApp.model.Enrollment;
import com.example.StudentsApp.model.Student;
import com.example.StudentsApp.repository.CourseRepository;
import com.example.StudentsApp.repository.EnrollmentRepository;
import com.example.StudentsApp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@SpringBootTest(classes = StudentsAppApplication.class)
public class EnrollmentTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    public void testCourseEnrollmentsRelationship() {

        Course course = new Course();
        course.setTitle("Java");
        course.setDescription("Advanced Java programming");
        course = courseRepository.save(course);

        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setEmail("john@gmail.com");
        student1 = studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Jane Doe");
        student2.setEmail("jane@gmail.com");
        student2 = studentRepository.save(student2);

        Student student3 = new Student();
        student3.setName("John Smith");
        student3.setEmail("john@yahoo.com");
        student3 = studentRepository.save(student3);

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setCourse(course);
        enrollment1.setStudent(student1);
        enrollment1.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment1);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setCourse(course);
        enrollment2.setStudent(student2);
        enrollment2.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment2);

        Enrollment enrollment3 = new Enrollment();
        enrollment3.setCourse(course);
        enrollment3.setStudent(student3);
        enrollment3.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment3);

        Course retrievedCourse = courseRepository.findById(course.getId()).orElse(null);
        assertThat(retrievedCourse).isNotNull();

        List<Enrollment> enrollmentsForCourse = enrollmentRepository.findByCourseId(course.getId());
        assertThat(enrollmentsForCourse).hasSize(3);
    }

    @Test
    public void testStudentEnrollmentsRelationship() {
        Course course = new Course();
        course.setTitle("OOP");
        course.setDescription("Learn Object Oriented Programming");
        course = courseRepository.save(course);

        Student student1 = new Student();
        student1.setName("Gigel");
        student1.setEmail("gigel@example.com");
        student1 = studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Ionel");
        student2.setEmail("ionel@example.com");
        student2 = studentRepository.save(student2);

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setCourse(course);
        enrollment1.setStudent(student1);
        enrollment1.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment1);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setCourse(course);
        enrollment2.setStudent(student2);
        enrollment2.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment2);

        List<Enrollment> enrollmentsForCourse = enrollmentRepository.findByCourseId(course.getId());
        assertThat(enrollmentsForCourse).hasSize(2);

        List<Student> studentsInCourse = enrollmentsForCourse.stream()
                .map(Enrollment::getStudent)
                .collect(Collectors.toList());

        assertThat(studentsInCourse).contains(student1, student2);
    }
}
