package com.gestioncursos.gestioncursos.Student.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestioncursos.gestioncursos.Course.Entity.Course;
import com.gestioncursos.gestioncursos.Course.Repository.CourseRepository;
import com.gestioncursos.gestioncursos.Exceptions.DuplicateResourceException;
import com.gestioncursos.gestioncursos.Exceptions.InvalidInputException;
import com.gestioncursos.gestioncursos.Exceptions.ResourceNotFoundException;
import com.gestioncursos.gestioncursos.Student.Entity.Student;
import com.gestioncursos.gestioncursos.Student.Repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student createStudent(Student student){

        if (student.getAge() <= 0 || student.getAge() > 150) {

            throw new InvalidInputException("Invalid age: " + student.getAge());
            
        }

        Optional<Student> existingStudentEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (existingStudentEmail.isPresent()) {

            throw new DuplicateResourceException("Email " + student.getEmail() + " Already Exists! ");

        }

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

    @Override
    public Student addCourseToStudent(Long studentId, Long courseId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }

    
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new EntityNotFoundException("Course not found with id: " + courseId);
        }
    
        // Obtener el estudiante y curso
        Student student = studentOptional.get();
        Course course = courseOptional.get();
    
        if(student.getCourses().contains(course)){

            throw new DuplicateResourceException("The Student " + student.getName() + " Already has this course! :)");

        }

        // Añadir el curso a la lista de cursos del estudiante
        student.getCourses().add(course);
    
        // Guardar el estudiante con la nueva relación
        return studentRepository.save(student);
    }

}
