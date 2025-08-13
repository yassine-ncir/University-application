package apps.university_app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "location")
    private String location;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Program> programs;


    public void addProgram(Program program) {
        programs.add(program);
        program.setDepartment(this);
    }

    public void removeProgram(Program program) {
        programs.remove(program);
        program.setDepartment(null);
    }

}
