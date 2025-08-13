package apps.university_app.model.entity;

import apps.university_app.model.enums.DegreeProgType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "programs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Program implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DegreeProgType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "program",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Speciality> specialities;

}
