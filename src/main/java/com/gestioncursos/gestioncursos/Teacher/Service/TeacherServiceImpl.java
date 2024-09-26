package com.gestioncursos.gestioncursos.Teacher.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestioncursos.gestioncursos.Teacher.Entity.Teacher;
import com.gestioncursos.gestioncursos.Teacher.Repository.TeacherRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {

        return teacherRepository.save(teacher);

    }

    @Override
    public void deleteTeacherById(Long id){

        teacherRepository.deleteById(id);

    }

    @Override
    public Teacher updateTeacher(Teacher teacher){

        if (teacherRepository.existsById(teacher.getId())) {
            return teacherRepository.save(teacher);
        }else{
            throw new EntityNotFoundException("Teacher not found");
        }

    }

    @Override
    public Teacher findTeacherById(Long id){

        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        }else{
            throw new EntityNotFoundException("Teacher not found! ");
        }

    }

    @Override
    public Page<Teacher> findAllTeachers(Pageable pageable){
        return teacherRepository.findAll(pageable);
    }

}
