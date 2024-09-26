package com.gestioncursos.gestioncursos.Teacher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestioncursos.gestioncursos.Teacher.Entity.Teacher;

                                //    EXTENDEMOS JPAREPOSITORY QUE HEREDA METODOS UTILES COMO : CREAR, ENCONTRAR, BORRAR ETC...
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
