package dev.patika.homework03.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String instructorName;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> instructorCourses;

    public Instructor(String instructorName, String address, String phoneNumber, List<Course> instructorCourses) {
        this.instructorName = instructorName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.instructorCourses = instructorCourses;
    }


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "instructor")
    private PermanentInstructor permanentInstructor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "instructor")
    private VisitingInstructor visitingInstructor;


}
