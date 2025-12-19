package com.kpl.registration.controller.NewLogic;

import com.kpl.registration.dto.NewLogic.*;
import com.kpl.registration.service.Student.Interfaces.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Student Api",description = "All the API's related to Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<GenericCreateResponseVO> createNewStudent(@RequestBody StudentCreateRequestVO studentCreateRequestVO) {
        var response = studentService.studentCreate(studentCreateRequestVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/AllStudent")
    public ResponseEntity<List<StudentListVO>> getAllStudent() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudent());
    }

    @PostMapping("/saveSubjects")
    public ResponseEntity<GenericCreateResponseVO> saveSubjects(@RequestBody List<SubjectRequestVO> subjectVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveSubject(subjectVO));
    }

    @GetMapping("/getStudentById/{studentId}")
    public ResponseEntity<SubjectResponseListVO> getStudentInfo(@PathVariable("studentId") Long studentId){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentInfo(studentId));
    }

    @GetMapping("/asyncCall")
    public ResponseEntity<GenericCreateResponseVO> asyncMethodTest() throws InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.CreateAsyncCall());
    }

}
