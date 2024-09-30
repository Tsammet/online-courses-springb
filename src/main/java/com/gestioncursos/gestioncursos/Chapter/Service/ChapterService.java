package com.gestioncursos.gestioncursos.Chapter.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gestioncursos.gestioncursos.Chapter.Entity.Chapter;

public interface ChapterService {

    Chapter createChapter (Chapter chapter); 

    void deleteChapter(Long id);

    Chapter updateChapter (Chapter chapter);

    Chapter findChapterById(Long id);

    Page<Chapter> findAllChapters(Pageable pageable);

}
