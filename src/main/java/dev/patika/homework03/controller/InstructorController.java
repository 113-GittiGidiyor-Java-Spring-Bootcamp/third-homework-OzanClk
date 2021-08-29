package dev.patika.homework03.controller;


import dev.patika.homework03.model.Course;
import dev.patika.homework03.model.Instructor;
import dev.patika.homework03.model.PermanentInstructor;
import dev.patika.homework03.model.VisitingInstructor;
import dev.patika.homework03.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InstructorController {


    private InstructorService instructorService;

    //Instructor Service CRUD Operations

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<Iterable<Instructor>> findAll() {

        return new ResponseEntity<>(instructorService.findByAll(), HttpStatus.OK);

    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Optional<Instructor>> findByIdInstructor(@PathVariable int id) {

        return new ResponseEntity<>(instructorService.findById(id), HttpStatus.OK);

    }

    @GetMapping("/instructors/contains")
    public ResponseEntity<Optional<Instructor>> findByContainsName(@RequestParam String containsName) {

        Optional<Instructor> instructor = instructorService.findByContainingName(containsName);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping("/instructors/find3HighestSalaryInstructors")
    public ResponseEntity<List<Instructor>> find3HighestSalaryInstructors() {

        List<Instructor> instructorList = instructorService.findBy3HighestSalaryInstructors();
        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }

    @GetMapping("/instructors/find3LowestSalaryInstructors")
    public ResponseEntity<List<Instructor>> find3LowestSalaryInstructors() {

        List<Instructor> instructorList = instructorService.findBy3LowestSalaryInstructors();
        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }


    @PostMapping("/instructors/{salarytype}")
    public ResponseEntity saveCourse(@PathVariable String salarytype, @RequestParam double amount, @RequestBody Instructor instructor) {

        //The only service written for hourly and monthly instructors.For this, salary type and amount values should be taken from the user.

        try {

            if (salarytype.equals("monthly")) {

                PermanentInstructor permanentInstructor = new PermanentInstructor();
                permanentInstructor.setInstructor(instructor);
                permanentInstructor.setMonthlySalary(amount);

                instructorService.save(permanentInstructor);
            } else if (salarytype.equals("hourly")) {

                VisitingInstructor visitingInstructor = new VisitingInstructor();
                visitingInstructor.setInstructor(instructor);
                visitingInstructor.setHourlySalary(amount);

                instructorService.save(visitingInstructor);
            }


            return ResponseEntity.ok(instructor);

        } catch (HttpClientErrorException e) {

            return ResponseEntity.status(e.getStatusCode()).body("Save Operation Failed");

        }

    }

    @PutMapping("/instructors")
    public ResponseEntity updateInstructor(@RequestBody Instructor instructor) {

        Optional<Instructor> tempStudent = instructorService.findById(instructor.getId());

        if (tempStudent.isPresent()) {
            return new ResponseEntity<>(instructorService.update(instructor), HttpStatus.OK);
        } else {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable int id) {

        instructorService.deleteById(id);
        return new ResponseEntity<>("Instructor with id number  " + id + " has been deleted", HttpStatus.OK);

    }

    @DeleteMapping("/instructors/{instructorName}")
    public ResponseEntity<String> deleteCourse(@PathVariable String instructorName) {
        instructorService.deleteByFullName(instructorName);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }


}
