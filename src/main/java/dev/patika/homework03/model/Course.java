package dev.patika.homework03.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    private String courseCode;
    private int courseCredit;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    @JsonIgnore
    private Instructor instructor;

    @ManyToMany(mappedBy = "studentCourseList")
    @JsonIgnore
    private List<Student> studentList;


}
