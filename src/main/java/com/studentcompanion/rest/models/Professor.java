package com.studentcompanion.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="professor")
public class Professor
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long professor_id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="professor")
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<ProfessorComment> professorComments;

    public Professor()
    {

    }

    public long getId() {
        return professor_id;
    }

    public void setId(long id) {
        this.professor_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
