package dev.patika.homework03.controller;


import dev.patika.homework03.model.Course;
import dev.patika.homework03.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Iterable<Course>> findAll() {

        return new ResponseEntity<>(courseService.findByAll(), HttpStatus.OK);

    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Optional<Course>> findByIdCourse(@PathVariable int id) {

        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);

    }

    @GetMapping("/courses/contains")
    public ResponseEntity<Optional<Course>> findByContainsName(@RequestParam String containsName) {

        Optional<Course> course = courseService.findByContainingName(containsName);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


    @PostMapping("/courses")
    public ResponseEntity saveCourse(@RequestBody Course course) {

        try {
            courseService.save(course);
            return ResponseEntity.ok(course);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Save Operation Failed");
        }

    }

    @PutMapping("/courses")
    public ResponseEntity updateCourse(@RequestBody Course course) {

        Optional<Course> tempStudent = courseService.findById(course.getId());

        if (tempStudent.isPresent()) {
            return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);
        } else {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseService.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseName}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseName) {
        courseService.deleteByFullName(courseName);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }


}
