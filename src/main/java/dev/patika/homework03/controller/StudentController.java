package dev.patika.homework03.controller;


import dev.patika.homework03.model.Instructor;
import dev.patika.homework03.model.Student;
import dev.patika.homework03.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    //Students Service CRUD Operations

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<Iterable<Student>> findAll() {
        return new ResponseEntity<>(studentService.findByAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Student>> findByIdStudent(@PathVariable int id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/students/contains")
    public ResponseEntity<Optional<Student>> findByContainsName(@RequestParam String containsName) {

        Optional<Student> student = studentService.findByContainingName(containsName);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getGenderWithGrouping")
    public List<?> getAgesWithGrouping() {
        return studentService.getGenderWithGrouping();
    }


    @PostMapping("/students")
    public ResponseEntity saveStudent(@RequestBody Student student) {

        try {
            studentService.save(student);
            return ResponseEntity.ok(student);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body("Save Operation Failed");
        }

    }

    @PutMapping("/students")
    public ResponseEntity updateStudent(@RequestBody Student student) {

        Optional<Student> tempStudent = studentService.findById(student.getId());

        if (tempStudent.isPresent()) {
            return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
        } else {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        studentService.deleteById(id);

        return new ResponseEntity<>("Student with id number  " + id + "has been deleted", HttpStatus.OK);

    }

    @DeleteMapping("/students/{studentName}")
    public ResponseEntity<String> deleteCourse(@PathVariable String studentName) {
        studentService.deleteByFullName(studentName);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }


}
