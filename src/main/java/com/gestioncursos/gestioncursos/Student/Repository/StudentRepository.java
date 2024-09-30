package com.gestioncursos.gestioncursos.Student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestioncursos.gestioncursos.Student.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

    Optional<Student> findStudentByEmail(String email);

}
