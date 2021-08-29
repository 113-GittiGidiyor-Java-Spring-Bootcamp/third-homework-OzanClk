package dev.patika.homework03.repository;


import dev.patika.homework03.model.Instructor;
import dev.patika.homework03.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByNameContains(String name);

    @Query(value = "select s.gender, count(s.gender) from Student s GROUP BY s.gender")
    List<?> getGenderWithGrouping();

    void deleteByFullName(String name);



}
