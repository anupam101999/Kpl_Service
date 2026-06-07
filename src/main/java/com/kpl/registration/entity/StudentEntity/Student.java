package com.kpl.registration.entity.StudentEntity;

import com.kpl.registration.entity.AllEntity.AuditTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Student extends AuditTable implements Serializable {
    private static final long serialVersionUID = 3980366880241829960L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_id")
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
