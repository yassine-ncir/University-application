package apps.university_app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number of students")
    private int numOfStud;
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "classe",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Student> students;
    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;
    @OneToMany(mappedBy = "classe",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Enrollment> enrollments;

}
