package com.kpl.registration.controller.NewLogic;

import com.kpl.registration.dto.NewLogic.StudentListVO;
import com.kpl.registration.service.Student.Interfaces.ContentStrategy;
import com.kpl.registration.service.Student.Interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theory")
public class TheoryController {

    @Autowired
    private StudentService studentService;

    @Autowired
    @Qualifier("video-content")
    private ContentStrategy contentStrategy;

    @GetMapping("/content")
    public ResponseEntity<String> getContent(){
        return ResponseEntity.status(HttpStatus.OK).body(contentStrategy.content());
    }

    @GetMapping("/caching/{dummy}")
    public ResponseEntity<List<StudentListVO>> getAllSubjectInfo(@PathVariable String dummy){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudentsCaching(dummy));
    }

    @GetMapping("/cacheInfo")
    public ResponseEntity<String> getCacheInfo(@RequestParam(name = "cache")String cacheName){
       return ResponseEntity.status(HttpStatus.OK).body(studentService.printCacheManger(cacheName));
    }
}
