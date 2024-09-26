package com.gestioncursos.gestioncursos.Student.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestioncursos.gestioncursos.Student.Entity.Student;
import com.gestioncursos.gestioncursos.Student.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    @Override 
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }


}
