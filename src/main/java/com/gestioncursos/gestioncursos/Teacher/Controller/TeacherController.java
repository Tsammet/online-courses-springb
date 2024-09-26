package com.gestioncursos.gestioncursos.Teacher.Controller;

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

import com.gestioncursos.gestioncursos.Teacher.Dto.TeacherDto;
import com.gestioncursos.gestioncursos.Teacher.Entity.Teacher;
import com.gestioncursos.gestioncursos.Teacher.Service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/create")                              //se usa para mandar datos en formato json en solicitudes post o put o delete       
    public ResponseEntity<String> createTeacher(@Valid @RequestBody TeacherDto teacherDto){
        
        Teacher teacher = new Teacher();

        // MAPEAMOS LOS DATOS DEL DTO EN LA ENTIDAD TEACHER
        teacher.setName(teacherDto.getName());
        teacher.setLastname(teacherDto.getLastname());
        teacher.setAge(teacherDto.getAge());
        teacher.setEmail(teacherDto.getEmail());

        Teacher newTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body("Teacher  Was created succesfully" + "\n" +
        "ID: " + newTeacher.getId()+ "\n" +
        "Name: " + newTeacher.getName() + "\n" +
        "Lastname: " + newTeacher.getLastname()+ "\n" +
        "Age: " + newTeacher.getAge()+ "\n" +
        "Email: " + newTeacher.getEmail());

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTeacher(@RequestBody TeacherDto teacherDto){

        Teacher teacher = teacherService.findTeacherById(teacherDto.getId());

        teacherService.deleteTeacherById(teacherDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body("Teacher " + teacher.getName() + " " + teacher.getLastname() +
        " was deleted succesfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTeacher(@Valid @RequestBody TeacherDto teacherDto){

        Teacher teacher = new Teacher();

        teacher.setId(teacherDto.getId());
        teacher.setName(teacherDto.getName());
        teacher.setLastname(teacherDto.getLastname());
        teacher.setAge(teacherDto.getAge());
        teacher.setEmail(teacherDto.getEmail());

        teacherService.updateTeacher(teacher);
        
        return ResponseEntity.ok("Teacher with id: " +  teacher.getId() +" was Updated succesfully");

    }

    @GetMapping("/findTeacher")
    public ResponseEntity<Teacher> findTeacher(@Valid @RequestBody TeacherDto teacherDto){
        Teacher teacher = teacherService.findTeacherById(teacherDto.getId());
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/findAllTeachers")
    public ResponseEntity<Page<Teacher>> findAllTeachers(Pageable pageable){
        Page<Teacher> teacherPage = teacherService.findAllTeachers(pageable);

        if (teacherPage.hasContent()) {
           return ResponseEntity.ok(teacherPage); 
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
