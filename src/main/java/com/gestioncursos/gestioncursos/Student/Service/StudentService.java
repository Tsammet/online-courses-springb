package com.gestioncursos.gestioncursos.Student.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestioncursos.gestioncursos.Student.Entity.Student;

public interface StudentService {

    Student createStudent(Student student);

    void deleteStudentById(Long id);

    Student updateStudent(Student student);

    Student findStudentById(Long id);

    Page<Student> findAllStudents(Pageable pageable);

    Student addCourseToStudent(Long studentId, Long courseId);

}
