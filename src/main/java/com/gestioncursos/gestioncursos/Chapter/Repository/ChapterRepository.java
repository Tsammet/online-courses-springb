package com.gestioncursos.gestioncursos.Chapter.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestioncursos.gestioncursos.Chapter.Entity.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Optional<Chapter> findByChapterName(String name);

}
