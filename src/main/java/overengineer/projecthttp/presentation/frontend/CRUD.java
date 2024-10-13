package overengineer.projecthttp.presentation.frontend;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface CRUD<I> {

    @GetMapping
    String findAll();

    String form(String id, Model model);

    @PostMapping
    String save(I dto, Model model);

}
