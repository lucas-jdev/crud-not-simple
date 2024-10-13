package overengineer.projecthttp.presentation.frontend.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput {
    private String id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}