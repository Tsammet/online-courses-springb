package com.gestioncursos.gestioncursos.Teacher.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// INDICA QUE ESTA CLASE CORRESPONBDE A UNA TABLA EN LA BASE DE DATOS
@Entity
// TABLE SIRVE PARA PONER EL NOMBRE QUE QUIERO MOSTRAR EN LA BASE DE DATOS
@Table(name = "teachers")
public class Teacher {

    // INDICA QUE ESTE CAMPO SERÁ LA PK DE LA TABLA
    @Id
    // INDICA QUE EL VALOR SE GENERARÁ AUTOMATICAMENTE POR LA BASE DE DATOS
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private int age;

    private String email;
    
    // DECLARO CONSTRUCTOR VACIO
    public Teacher() {
    }


    // DECLARO GETTERS Y SETTERS

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
