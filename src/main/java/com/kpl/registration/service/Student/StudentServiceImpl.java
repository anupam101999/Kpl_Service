package com.kpl.registration.service.Student;

import com.kpl.registration.dto.NewLogic.*;
import com.kpl.registration.entity.StudentEntity.Student;
import com.kpl.registration.entity.StudentEntity.Subject;
import com.kpl.registration.repository.Student.StudentRepo;
import com.kpl.registration.repository.Student.SubjectRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.kpl.registration.util.CommonConstants.STUDENT_CREATED;
import static com.kpl.registration.util.CommonConstants.STUDENT_EXIST;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GenericCreateResponseVO studentCreate(StudentCreateRequestVO studentCreateResponseVO) {
        var studentId = 0L;
        var message = "";
        var studentExist = studentRepo.existingStuWithSameName(studentCreateResponseVO.getFirstName(), studentCreateResponseVO.getLastName());
        if (studentExist.isEmpty()) {
            var student = modelMapper.map(studentCreateResponseVO, Student.class);
            var response = studentRepo.save(student);
            studentId = response.getStudentId();
            message = STUDENT_CREATED;
            log.info("new Student Created");
        } else {
            message = STUDENT_EXIST;
            log.info("Student Exists");
            studentId = studentExist.get(0).getStudentId();
        }
        return new GenericCreateResponseVO(String.valueOf(studentId), message);
    }

    @Override
    public List<StudentListVO> getAllStudent() {
        var studentList = new ArrayList<StudentListVO>();
        var allStudent = studentRepo.findAll();
        allStudent.forEach(stu -> {
            var student = modelMapper.map(stu, StudentListVO.class);
            studentList.add(student);
        });
        return studentList;
    }

    @Override
    public GenericCreateResponseVO saveSubject(List<SubjectRequestVO> subjectListVO) {
        List<Long> ids = new ArrayList<>();
        for (SubjectRequestVO subjectVO : subjectListVO) {
            var subject = new Subject();
            var student = studentRepo.findById(subjectVO.getStudentId());

            if (student.isPresent()) {
                subject.setUsn(UUID.randomUUID().toString());
                subject.setSubject(subjectVO.getSubject());
                subject.setMarks(subjectVO.getMarks());
                subject.setStudent(student.get());
                var res = subjectRepo.save(subject);
                ids.add(res.getSubjectId());
            }
        }
        return new GenericCreateResponseVO(ids.stream().map(String::valueOf).collect(Collectors.joining(",")), ids.isEmpty() ? "No Student Found" : "Subjects added");

    }

    @Override
    public SubjectResponseListVO getStudentInfo(Long studentId) {
        var responseListVO = new SubjectResponseListVO();
        var subList = new ArrayList<SubjectResponseVO>();

        var subjectList = subjectRepo.findSubjectByStudentId(studentId);
        if (!subjectList.isEmpty()) {
            responseListVO.setStudentId(subjectList.get(0).getStudent().getStudentId());
            responseListVO.setName(subjectList.get(0).getStudent().getFirstName() + " " + subjectList.get(0).getStudent().getLastName());
            for (var subject : subjectList) {
                var response = modelMapper.map(subject, SubjectResponseVO.class);
                subList.add(response);
            }

            responseListVO.setAverage(subList.stream().mapToLong(SubjectResponseVO::getMarks).average().getAsDouble());
            responseListVO.setSubjectRes(subList);
        }
        return responseListVO;
    }
}
