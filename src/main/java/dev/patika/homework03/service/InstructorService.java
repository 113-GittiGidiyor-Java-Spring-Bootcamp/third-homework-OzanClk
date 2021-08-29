package dev.patika.homework03.service;

import dev.patika.homework03.model.Course;
import dev.patika.homework03.repository.InstructorRepository;
import dev.patika.homework03.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService implements BaseService<Instructor> {

    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


    @Override
    public Optional<Instructor> save(Instructor instructor) {
        return Optional.of(instructorRepository.save(instructor));
    }

    @Override
    public Optional<Instructor> findById(int id) {
        return instructorRepository.findById(id);
    }

    @Override
    public Optional<Instructor> update(Instructor instructor) {
        return Optional.of(instructorRepository.save(instructor));
    }

    @Override
    public Iterable<Instructor> findByAll() {
        return instructorRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        instructorRepository.deleteById(id);
    }

    public Optional<Instructor> findByContainingName(String containsName) {
        return Optional.of(instructorRepository.findByInstructorNameContains(containsName));
    }

    public List<Instructor> findBy3HighestSalaryInstructors() {

        List<Instructor> instructorList = instructorRepository.findBy3HighestSalaryInstructors();

        return instructorList;
    }

    public List<Instructor> findBy3LowestSalaryInstructors() {

        List<Instructor> instructorList = instructorRepository.findBy3LowesttSalaryInstructors();

        return instructorList;
    }

    public void deleteByFullName(String instructorName) {
        instructorRepository.deleteByFullName(instructorName);
    }

}
