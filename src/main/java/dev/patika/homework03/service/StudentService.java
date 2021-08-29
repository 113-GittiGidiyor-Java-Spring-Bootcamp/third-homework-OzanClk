package dev.patika.homework03.service;

import dev.patika.homework03.model.Instructor;
import dev.patika.homework03.repository.StudentRepository;
import dev.patika.homework03.model.Student;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements BaseService<Student> {


    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Optional<Student> save(Student student) {
        return Optional.of(studentRepository.save(student));
    }

    @Override
    public Optional<Student> findById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student;
    }

    @Override
    public Optional<Student> update(Student student) {
        return Optional.of(studentRepository.save(student));
    }

    @Override
    public Iterable<Student> findByAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> findByContainingName(String containsName) {
        return Optional.of(studentRepository.findByNameContains(containsName));
    }

    public List<?> getGenderWithGrouping() {
        return studentRepository.getGenderWithGrouping();
    }

    public void deleteByFullName(String instructorName) {
        studentRepository.deleteByFullName(instructorName);
    }

}
