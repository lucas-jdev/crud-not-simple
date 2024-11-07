package overengineer.projecthttp.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Represent an entity.
 * @param <I> type of id
 */
@Getter
@Setter
public abstract class Entity<I> {

    private I id;

}
