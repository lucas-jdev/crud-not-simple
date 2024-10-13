package overengineer.projecthttp.presentation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * CRUD
 * type interface is a contract for the CRUD operations
 * @param <I> input type
 * @param <O> output type
 */
public interface CRUD<I, O> {

    @PostMapping
    ResponseEntity<O> create(I dto);

    @GetMapping("/{id}")
    ResponseEntity<O> read(@PathVariable String id);

    @GetMapping
    ResponseEntity<Collection<O>> readAll();

    @PutMapping("/{id}")
    ResponseEntity<O> update(@PathVariable String id, @RequestBody I dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);

}
