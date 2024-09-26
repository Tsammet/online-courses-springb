package com.gestioncursos.gestioncursos.Student.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestioncursos.gestioncursos.Student.Entity.Student;
import com.gestioncursos.gestioncursos.Student.Repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

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

    @Override
    public Student updateStudent(Student student){
        
        if (studentRepository.existsById(student.getId())) {
            
            return studentRepository.save(student);

        }else{

            throw new EntityNotFoundException("Student not found");

        }

    }

    @Override
    public Student findStudentById(Long id){

        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            return student.get();
        }else{
            throw new EntityNotFoundException("Student not found!");
        }

    }

    @Override
    public Page<Student> findAllStudents(Pageable pageable){

        return studentRepository.findAll(pageable);

    }

}
