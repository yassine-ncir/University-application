package apps.university_app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static apps.university_app.model.enums.DegreeProgType.*;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Speciality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private int duration;
    @Column(name = "available")
    private Boolean available ;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    @OneToMany(mappedBy = "speciality",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Grade> grades;


}
