package com.gestioncursos.gestioncursos.Course.Controller;

import java.util.Optional;

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

import com.gestioncursos.gestioncursos.Course.Dto.CourseDto;
import com.gestioncursos.gestioncursos.Course.Entity.Course;
import com.gestioncursos.gestioncursos.Course.Service.CourseService;
import com.gestioncursos.gestioncursos.Exceptions.ResourceNotFoundException;
import com.gestioncursos.gestioncursos.Teacher.Entity.Teacher;
import com.gestioncursos.gestioncursos.Teacher.Repository.TeacherRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@Valid @RequestBody CourseDto courseDto){

        Optional<Teacher> existTeacher = teacherRepository.findById(courseDto.getTeacherId());

        if (!existTeacher.isPresent()) {

            throw new ResourceNotFoundException("Teacher not found!");
            
        }

        Course course = new Course();

        course.setCourseName(courseDto.getCourseName());
        course.setTeachers(existTeacher.get());

        Course newCourse = courseService.createCourse(course);

        return ResponseEntity.status(HttpStatus.CREATED).body("Course " + newCourse.getCourseName() + " was Created Successfully");

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestBody CourseDto courseDto){

        courseService.deleteCourse(courseDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body("Course with id: " + courseDto.getId() + " was deleted successfully" );
        
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@Valid @RequestBody CourseDto courseDto){

        Course course = new Course();

        course.setId(courseDto.getId());
        course.setCourseName(courseDto.getCourseName());

        courseService.updateCourse(course);
        return ResponseEntity.ok("Course with id: " + course.getId() + " Was updated successfully");

    }

    @GetMapping("/findCourse")
    public ResponseEntity<Course> findCourseById(@Valid @RequestBody CourseDto courseDto){

        Course course = courseService.findCourseById(courseDto.getId());

        return ResponseEntity.ok(course);

    }

    @GetMapping("/findAllCourses")
    public ResponseEntity<Page<Course>> findAllCourses(Pageable pageable){

        Page<Course> courses = courseService.findAllCourses(pageable);

        if (courses.hasContent()) {
            return ResponseEntity.ok(courses);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
