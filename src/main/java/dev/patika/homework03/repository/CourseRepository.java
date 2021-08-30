package dev.patika.homework03.repository;

import dev.patika.homework03.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findByCourseNameContains(String name);

    void deleteByCourseName(String name);


}
