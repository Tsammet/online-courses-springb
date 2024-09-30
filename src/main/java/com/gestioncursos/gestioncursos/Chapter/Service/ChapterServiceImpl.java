package com.gestioncursos.gestioncursos.Chapter.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestioncursos.gestioncursos.Chapter.Entity.Chapter;
import com.gestioncursos.gestioncursos.Chapter.Repository.ChapterRepository;
import com.gestioncursos.gestioncursos.Exceptions.DuplicateResourceException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired ChapterRepository chapterRepository;

    @Override 
    public Chapter createChapter(Chapter chapter){

        Optional<Chapter> existingChapterName = chapterRepository.findByChapterName(chapter.getChapterName());

        if (existingChapterName.isPresent()) {
            
            throw new DuplicateResourceException("The Chapter with name: " + chapter.getChapterName() + " Already exists!");

        }

        return chapterRepository.save(chapter);

    }

    @Override
    public void deleteChapter(Long id){

        chapterRepository.deleteById(id);

    }

    @Override
    public Chapter updateChapter(Chapter chapter){

        if (chapterRepository.existsById(chapter.getId())) {

            return chapterRepository.save(chapter);

        }else{

            throw new EntityNotFoundException("Chapter was not found!");
 
        }

    }

    @Override
    public Chapter findChapterById(Long id){

        Optional<Chapter> chapter = chapterRepository.findById(id);

        if (chapter.isPresent()) {

            return chapter.get();

        }else{

            throw new EntityNotFoundException("Chapter not found! :(");

        }

    }

    @Override
    public Page<Chapter> findAllChapters(Pageable pageable){

        return chapterRepository.findAll(pageable);

    }


}
