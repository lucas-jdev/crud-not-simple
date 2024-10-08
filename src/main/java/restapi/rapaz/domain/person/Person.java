package restapi.rapaz.domain.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import restapi.rapaz.domain.exception.BusinessException;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private Integer year;
    private Boolean majorYear;

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException("Nome não pode ser nulo ou vazio");
        }
        this.name = name;
    }

    private void setMajorYear() {
        if (year < 18) {
            throw new BusinessException("Idade não pode ser menor que 18");
        }
        this.majorYear = this.year >= 18;
    }

    public void setLastName(String sobrenome) {
        if (sobrenome == null || sobrenome.isBlank()) {
            throw new BusinessException("Sobrenome não pode ser nulo ou vazio");
        }
        this.lastName = sobrenome;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new BusinessException("Email não pode ser nulo ou vazio");
        }
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        if (!pattern.matcher(email).matches()) {
            throw new BusinessException("Email inválido");
        }
        this.email = email;
    }

    public void setYear(Integer year) {
        if (year == null || year < 0) {
            throw new BusinessException("Idade não pode ser nula ou negativa");
        }
        if (year > 80) {
            throw new BusinessException("Idade não pode ser maior que 150");
        }
        if (year < 18) {
            throw new BusinessException("Idade não pode ser menor que 18");
        }
        this.year = year;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hibernateProxy? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Person pessoa = (Person) o;
        return getId() != null && Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public final int hashCode() {
        if (this instanceof HibernateProxy hibernateProxy) {
            return hibernateProxy.getHibernateLazyInitializer().getPersistentClass().hashCode();
        }
        return getClass().hashCode();
    }
}
