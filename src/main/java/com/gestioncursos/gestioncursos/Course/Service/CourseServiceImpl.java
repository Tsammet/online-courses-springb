package com.gestioncursos.gestioncursos.Course.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestioncursos.gestioncursos.Course.Entity.Course;
import com.gestioncursos.gestioncursos.Course.Repository.CourseRepository;
import com.gestioncursos.gestioncursos.Exceptions.DuplicateResourceException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course){

        Optional<Course> courseExistName = courseRepository.findByCourseName(course.getCourseName());

        if (courseExistName.isPresent()) {
            throw new DuplicateResourceException("Course with name " + course.getCourseName() + " Already Exists!");
        }

        return courseRepository.save(course);

    }

    @Override
    public void deleteCourse(Long id){

        courseRepository.deleteById(id);

    }

    @Override
    public Course updateCourse(Course course){

        if (courseRepository.existsById(course.getId())) {

            return courseRepository.save(course);

        }else{

            throw new EntityNotFoundException("Course with id: " + course.getId() + " was not found!");

        }

    }

    @Override
    public Course findCourseById(Long id){

        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            
            return course.get();

        }else{

            throw new EntityNotFoundException("Entity not found!");

        }

    }

    @Override
    public Page<Course> findAllCourses(Pageable pageable){

        return courseRepository.findAll(pageable);

    }

}
