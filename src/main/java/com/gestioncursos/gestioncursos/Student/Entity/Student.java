package com.gestioncursos.gestioncursos.Student.Entity;

import java.util.List;

import com.gestioncursos.gestioncursos.Course.Entity.Course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // COLUMN NAME sive para poner el nombre del campo que saldrá en la bd
    @Column(name = "name")
    private String name;
    private String lastname;
    private String email;
    private int age;

    // fetch = FetchType.EAGER: Esto significa que cuando se carga un estudiante desde la base de datos, también se cargarán automáticamente todos los cursos asociados a ese estudiante.
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        // name para definir como se llamará la tablaa intermedia || luego la primera columna que va a tener que hace relación al id del estudiante con el nombre student_id
        name = "student_course", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
        // Luego se hace el inverse que es contra la clase que haré la relación, course_id como se llamará el atributo y la referencia "id " a la tabla Course
        inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;

    // EL CONSTRUCTOR VACIO FACILITA LA CREACIÓN DE INSTANCIAS
    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
