package com.kpl.registration.TestService;

import com.kpl.registration.dto.NewLogic.*;
import com.kpl.registration.entity.StudentEntity.Student;
import com.kpl.registration.entity.StudentEntity.Subject;
import com.kpl.registration.repository.Student.StudentRepo;
import com.kpl.registration.repository.Student.SubjectRepo;
import com.kpl.registration.service.Student.AsyncCallService;
import com.kpl.registration.service.Student.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;
    @InjectMocks
    private AsyncCallService asyncCallSer;
    @Mock
    private AsyncCallService asyncCallService;
    @Mock
    private StudentRepo studentRepo;
    @Mock
    private SubjectRepo subjectRepo;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testAdd() {
        assertEquals(4, 2 + 2);
    }

    List<Student> studentList = List.of(new Student(1L, "fName1", "lName1"),
            new Student(2L, "fName2", "lName2"));

    Student student = new Student(1L, "fName1", "lName1");

    List<SubjectRequestVO> subjectListDto = List.of(new SubjectRequestVO(1L, "Math", 1L),
            new SubjectRequestVO(2L, "Eng", 2L));

    Subject subject = new Subject(1L, "USN", "subject", 90L, student);

    SubjectResponseVO subjectResponseVO = new SubjectResponseVO(1L, "USN", "subject", 90L);

    @Test
    public void testGetAllStudent() {

        when(studentRepo.findAll()).thenReturn(studentList);
        List<StudentListVO> stuList = studentServiceImpl.getAllStudent();
        assertEquals(2, stuList.size());
    }

    @Test
    public void testStudentCreate() {
        var stuVo = new StudentCreateRequestVO("firstName", "LastName");

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                when(studentRepo.existingStuWithSameName(anyString(), anyString())).thenReturn(studentList);
                studentServiceImpl.studentCreate(stuVo);
            } else {
                when(studentRepo.existingStuWithSameName(anyString(), anyString()))
                        .thenReturn(Collections.emptyList());
                when(modelMapper.map(stuVo, Student.class)).thenReturn(student);
                when(studentRepo.save(any(Student.class))).thenReturn(student);
                studentServiceImpl.studentCreate(stuVo);
            }
        }
    }

    @Test
    public void testSaveSubject() {
        when(studentRepo.findById(anyLong())).thenReturn(Optional.of(student));
        when(subjectRepo.save(any(Subject.class))).thenReturn(subject);
        studentServiceImpl.saveSubject(subjectListDto);
    }

    @Test
    public void testGetStudentInfo() {

        Subject math = new Subject();
        math.setSubjectId(10L);
        math.setSubject("Math");
        math.setStudent(student);

        when(subjectRepo.findSubjectByStudentId(anyLong())).thenReturn(List.of(math));
        when(modelMapper.map(math, SubjectResponseVO.class)).thenReturn(subjectResponseVO);
        studentServiceImpl.getStudentInfo(anyLong());

    }

    @Test
    public void testJunit() {
        var sen = "Find second highest num";
        var res = studentServiceImpl.junitTest(sen);
        assertEquals("second", res);
    }

    @Test
    void testCreateAsyncCall() throws InterruptedException {
        long start = System.currentTimeMillis();
        GenericCreateResponseVO response = studentServiceImpl.CreateAsyncCall();
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration < 10000, "Method took too long, async not working");
        assertEquals("id", response.getId());
        assertEquals("Generic Response", response.getResponse());

        verify(asyncCallService, times(1)).asyncCall();
    }
}
