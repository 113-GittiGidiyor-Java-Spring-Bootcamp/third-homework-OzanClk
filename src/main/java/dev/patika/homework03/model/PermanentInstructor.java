package dev.patika.homework03.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermanentInstructor extends Instructor {

    private double monthlySalary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;


}
