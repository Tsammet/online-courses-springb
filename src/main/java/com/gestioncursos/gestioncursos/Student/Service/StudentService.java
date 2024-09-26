package com.gestioncursos.gestioncursos.Student.Service;

import com.gestioncursos.gestioncursos.Student.Entity.Student;

public interface StudentService {

    Student createStudent(Student student);

    void deleteStudentById(Long id);

}
