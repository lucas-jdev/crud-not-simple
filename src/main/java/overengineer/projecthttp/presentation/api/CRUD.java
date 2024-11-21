package overengineer.projecthttp.presentation.api;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * CRUD
 * type interface is a contract for the CRUD operations
 * @param <I> input type
 * @param <O> output type
 */
public interface CRUD<I, O> {

    @PostMapping
    O create(@RequestBody I dto);

    @GetMapping("/{id}")
    O read(@PathVariable String id);

    @GetMapping
    Collection<O> readAll(@RequestParam Map<String, Object> queryParams);

    @PutMapping("/{id}")
    O update(@PathVariable String id, @RequestBody I dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id);

}
