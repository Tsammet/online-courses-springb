package com.gestioncursos.gestioncursos.Student.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestioncursos.gestioncursos.Student.Dto.AddCourseToStudentDto;
import com.gestioncursos.gestioncursos.Student.Dto.StudentDto;
import com.gestioncursos.gestioncursos.Student.Entity.Student;
import com.gestioncursos.gestioncursos.Student.Service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@Valid @RequestBody StudentDto studentDto){

        Student student = new Student();

        student.setName(studentDto.getName());
        student.setLastname(studentDto.getLastname());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());

        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student " + newStudent.getName() + " " + newStudent.getLastname() +
        " Was created successfully! :) ");

    } 

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestBody StudentDto studentDto){

        studentService.deleteStudentById(studentDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Student was deleted successfully");

    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentDto studentDto){

        Student student = new Student();

        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setLastname(studentDto.getLastname());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());

        studentService.updateStudent(student);

        return ResponseEntity.ok("Student with id: " + student.getId() + " was updated successfully");

    }

    @GetMapping("/findStudent")
    public ResponseEntity<Student> findStudentById(@Valid @RequestBody StudentDto studentDto){

        Student student = studentService.findStudentById(studentDto.getId());
        return ResponseEntity.ok(student);

    }

    @GetMapping("/findAllStudents")
    public ResponseEntity<Page<Student>> findAllStudents(Pageable pageable){

        Page<Student> student = studentService.findAllStudents(pageable);

        if (student.hasContent()) {
         
            return ResponseEntity.ok(student);

        }else{

            return ResponseEntity.notFound().build();
            
        }

    }

    @PostMapping("/addCourseToStudent")
    public ResponseEntity<String> addCourseToStudent(@RequestBody AddCourseToStudentDto dto) {
        studentService.addCourseToStudent(dto.getStudentId(), dto.getCourseId());
        return ResponseEntity.ok("Course added to student successfully.");
    }
}
