package com.gestioncursos.gestioncursos.Student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestioncursos.gestioncursos.Student.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
