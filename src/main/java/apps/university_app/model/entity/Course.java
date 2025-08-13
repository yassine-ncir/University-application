package apps.university_app.model.entity;

import apps.university_app.model.enums.Semester;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "code",unique = true,nullable = false)
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "credits")
    private int credits;
    @Column(name = "total hours")
    private Double totalHours;
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Enrollment> enrollments;
    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;



    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private String generateCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
    @PrePersist
    @PreUpdate
    public void generateUniqueCode() {
        if (this.code == null || this.code.isEmpty()) {
            this.code = generateCode(5);
        }
    }



}
