package com.kpl.registration.repository.Student;

import com.kpl.registration.entity.StudentEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.firstName=:firstName and s.lastName=:lastName")
    List<Student> existingStuWithSameName(String firstName, String lastName);


}
