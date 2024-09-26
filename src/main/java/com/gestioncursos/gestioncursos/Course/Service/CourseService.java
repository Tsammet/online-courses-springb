package com.gestioncursos.gestioncursos.Course.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestioncursos.gestioncursos.Course.Entity.Course;

public interface CourseService {

    Course createCourse(Course course);

    void deleteCourse(Long id);

    Course updateCourse(Course course);

    Course findCourseById(Long id);

    Page<Course> findAllCourses(Pageable pageable);

}
