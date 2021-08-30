package dev.patika.homework03.service;


import dev.patika.homework03.repository.CourseRepository;
import dev.patika.homework03.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements BaseService<Course> {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Optional<Course> save(Course course) {
        return Optional.of(courseRepository.save(course));
    }

    @Override
    public Optional<Course> findById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course;
    }

    @Override
    public Optional<Course> update(Course course) {
        return Optional.of(courseRepository.save(course));
    }

    @Override
    public Iterable<Course> findByAll() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    public Optional<Course> findByContainingName(String containsName) {
        return Optional.of(courseRepository.findByCourseNameContains(containsName));
    }

    public void deleteByCourseName(String courseName) {
        courseRepository.deleteByCourseName(courseName);
    }


}
