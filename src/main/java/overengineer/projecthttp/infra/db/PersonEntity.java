package overengineer.projecthttp.infra.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_person")
public class PersonEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "tb_person_seq", sequenceName = "tb_person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_person_seq")
    @Column(name = "p_id")
    private Long id;
    @Column(name = "p_public_id", unique = true)
    private UUID publicId;
    @Column(name = "p_name")
    private String name;
    @Column(name = "p_last_name")
    private String lastName;
    @Column(name = "p_email")
    private String email;
    @Column(name = "p_birth_date")
    private LocalDate birthDate;
    @Column(name = "p_active")
    private boolean active;

}
