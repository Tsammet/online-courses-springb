package com.gestioncursos.gestioncursos.Chapter.Controller;

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

import com.gestioncursos.gestioncursos.Chapter.Dto.ChapterDto;
import com.gestioncursos.gestioncursos.Chapter.Entity.Chapter;
import com.gestioncursos.gestioncursos.Chapter.Service.ChapterService;
import com.gestioncursos.gestioncursos.Course.Entity.Course;
import com.gestioncursos.gestioncursos.Course.Repository.CourseRepository;
import com.gestioncursos.gestioncursos.Exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private  CourseRepository courseRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createChapter(@Valid @RequestBody ChapterDto chapterDto){

        Course course = courseRepository.findById(chapterDto.getCourseId())
        .orElseThrow(() -> new ResourceNotFoundException("Course not found!"));

        Chapter chapter = new Chapter();

        chapter.setChapterName(chapterDto.getChapterName());
        chapter.setCourses(course);

        Chapter newChapter = chapterService.createChapter(chapter);
        return ResponseEntity.status(HttpStatus.CREATED).body("Chapter " + newChapter.getChapterName() + " Was created Succesfully ! :)");

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteChapter(@Valid @RequestBody ChapterDto chapterDto){

        chapterService.deleteChapter(chapterDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body("Chapter with id : " + chapterDto.getId()
         + " Was deleted successfully! :)");

    }

    @PutMapping("/update")
    public ResponseEntity<String> updateChapter(@Valid @RequestBody ChapterDto chapterDto){

        Chapter chapter = chapterService.findChapterById(chapterDto.getId());
        if (chapter == null) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found with id: " + chapterDto.getId());

        }

        if (chapterDto.getCourseId() != null) {
            
            Course course = courseRepository.findById(chapterDto.getCourseId())
            .orElseThrow(() -> new ResourceNotFoundException("chapter not found with id: " + chapterDto.getCourseId()));

            chapter.setCourses(course);

        }
        chapter.setChapterName(chapterDto.getChapterName());

        chapterService.updateChapter(chapter);
    
        return ResponseEntity.ok("Chapter with id: " + chapter.getId() + " was updated successfully");

    }

    @GetMapping("/findChapter")
    public ResponseEntity<Chapter> findChapterById(@Valid @RequestBody ChapterDto chapterDto){

        Chapter chapter = chapterService.findChapterById(chapterDto.getId());

        return ResponseEntity.ok(chapter);

    }

    @GetMapping("/allChapters")
    public ResponseEntity<Page<Chapter>> findAllChapter(Pageable pageable){

        Page<Chapter> chapter= chapterService.findAllChapters(pageable);

        if (chapter.hasContent()) {
            return ResponseEntity.ok(chapter);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
