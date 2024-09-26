package com.gestioncursos.gestioncursos.Teacher.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestioncursos.gestioncursos.Teacher.Entity.Teacher;

public interface TeacherService {

// Cuando devuelva será un objeto del tipo Teacher
    Teacher createTeacher(Teacher teacher);

    void deleteTeacherById(Long id);

    Teacher updateTeacher(Teacher teacher);

    Teacher findTeacherById(Long id);

    Page<Teacher> findAllTeachers(Pageable pageable); 

}
