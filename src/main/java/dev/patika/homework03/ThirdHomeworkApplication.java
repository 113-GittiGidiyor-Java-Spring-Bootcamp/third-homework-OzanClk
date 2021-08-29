package dev.patika.homework03;

import dev.patika.homework03.model.Course;
import dev.patika.homework03.model.Instructor;
import dev.patika.homework03.model.Student;
import dev.patika.homework03.model.VisitingInstructor;
import dev.patika.homework03.repository.CourseRepository;
import dev.patika.homework03.repository.InstructorRepository;
import dev.patika.homework03.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class ThirdHomeworkApplication implements CommandLineRunner {

    @Bean
    public RestTemplate restTemplateBuilderr(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final InstructorRepository instructorRepository;

    @Autowired
    private final StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(ThirdHomeworkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("CommandLine Running");
        loadDataH2Database();


    }

    public void loadDataH2Database() {

        List<Course> courseList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();


        Student student = new Student();

        student.setName("Ahmet");
        student.setAddress("Istanbul");
        student.setGender("Male");

        studentList.add(student);


        Course course = new Course();

        course.setCourseName("Science");
        course.setCourseCode("Sci-1");
        course.setCourseCredit(4);


        Instructor instructor = new Instructor();
        VisitingInstructor visitingInstructor = new VisitingInstructor();

        instructor.setInstructorName("Mehmet");
        instructor.setAddress("Istanbul");
        instructor.setPhoneNumber("5553424313");

        visitingInstructor.setHourlySalary(200);

        instructor.setVisitingInstructor(visitingInstructor);


        course.setStudentList(studentList);
        course.setInstructor(instructor);
        courseList.add(course);
        student.setStudentCourseList(courseList);
        instructor.setInstructorCourses(courseList);


        courseRepository.save(course);
        studentRepository.save(student);
        instructorRepository.save(instructor);
    }

}
