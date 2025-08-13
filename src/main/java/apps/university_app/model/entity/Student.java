package apps.university_app.model.entity;

import apps.university_app.model.enums.DegreeProgType;
import apps.university_app.model.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "cin",nullable = false)
    private String cin;
    @Column(name = "date of birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private DegreeProgType degreeType;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classe;

}
