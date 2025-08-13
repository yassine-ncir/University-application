package apps.university_app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "grades")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    private int year; // the number of year in speciality
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;
    @OneToMany(mappedBy = "grade",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Class> classes;

}
