package overengineer.projecthttp.domain.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import overengineer.projecthttp.domain.DeleteLogic;
import overengineer.projecthttp.domain.Entity;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Person extends Entity<UUID> implements DeleteLogic {

    private Name name;
    private LastName lastName;
    private Email email;
    private BirthDate birthDate;
    private boolean active;

    public Person() {
        super.setId(UUID.randomUUID());
        this.active = true;
    }

    public Person(String name, String lastName, String email, LocalDate birthDate) {
        super.setId(UUID.randomUUID());
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setBirthDate(birthDate);
        this.active = true;
    }

    public Person(UUID id, String name, String lastName, String email, LocalDate birthDate, boolean active) {
        super.setId(id);
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setBirthDate(birthDate);
        this.active = active;
    }

    public void setName(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return this.name.value();
    }

    public void setLastName(String lastName) {
        this.lastName = new LastName(lastName);
    }

    public String getLastName() {
        return this.lastName.value();
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getEmail() {
        return this.email.value();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = new BirthDate(birthDate);
    }

    public LocalDate getBirthDate() {
        return this.birthDate.value();
    }

    public Integer getYear() {
        return this.birthDate.age();
    }

    public void inactive() {
        this.active = false;
    }

}
