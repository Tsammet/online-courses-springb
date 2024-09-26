package com.gestioncursos.gestioncursos.Course.Entity;


import java.util.List;

import com.gestioncursos.gestioncursos.Student.Entity.Student;
import com.gestioncursos.gestioncursos.Teacher.Entity.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    // Relacion DE MUCHOS CURSOS TIENEN UN PROFESOR Y UN PROFESOR TIENE MUCHOS CURSOS 
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teachers;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher teachers) {
        this.teachers = teachers;
    }



}
