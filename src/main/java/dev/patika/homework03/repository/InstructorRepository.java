package dev.patika.homework03.repository;


import dev.patika.homework03.model.Course;
import dev.patika.homework03.model.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

    Instructor findByInstructorNameContains(String name);

    @Query(value = "SELECT instructor_name,address,phone_number FROM instructor i INNER JOIN permanent_instructor pi ON  pi.instructor_id=i.id order by monthly_salary desc limit 0,3", nativeQuery = true)
    List<Instructor> findBy3HighestSalaryInstructors();

    @Query(value = "SELECT instructor_name,address,phone_number FROM instructor i INNER JOIN permanent_instructor pi ON  pi.instructor_id=i.id order by monthly_salary asc limit 0,3", nativeQuery = true)
    List<Instructor> findBy3LowesttSalaryInstructors();

    void deleteByFullName(String name);


}
