package com.gestioncursos.gestioncursos.Course.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestioncursos.gestioncursos.Course.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
