package com.kpl.registration.service.Student.Interfaces;

import com.kpl.registration.dto.NewLogic.*;

import java.util.List;

public interface StudentService {
    GenericCreateResponseVO studentCreate(StudentCreateRequestVO studentCreateResponseVO);

    List<StudentListVO> getAllStudent();

    GenericCreateResponseVO saveSubject(List<SubjectRequestVO> subjectVO);

    SubjectResponseListVO getStudentInfo(Long studentId);

    GenericCreateResponseVO CreateAsyncCall() throws InterruptedException;

    List<StudentListVO> getAllStudentsCaching(String dummy);

    String printCacheManger(String cacheManager);
}
