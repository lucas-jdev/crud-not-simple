package overengineer.projecthttp.presentation.frontend.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import overengineer.projecthttp.infra.exception.ApplicationException;
import overengineer.projecthttp.infra.use_case.crud.person.*;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonFound;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonInsert;
import overengineer.projecthttp.infra.use_case.crud.person.dto.PersonSetUpdate;
import overengineer.projecthttp.presentation.frontend.CRUD;

import java.util.UUID;

/**
 * People is a frontend controller that handles the people page.
 */
@Controller
@RequestMapping("/people")
public record PeopleController(
    SavePerson savePerson,
    FindPerson findPerson,
    UpdatePerson updatePerson
) implements CRUD<PersonInput> {

    private static final String PEOPLE_INDEX = "people/index";
    private static final String PEOPLE_FORM = "people/form";
    private static final String REDIRECT_PEOPLE = "redirect:/people";

    private static final String FIELD_PERSON = "person";

    @Override
    public String findAll() {
        return PEOPLE_INDEX;
    }

    @GetMapping("/form")
    @Override
    public String form(@RequestParam(required = false, name = "id") String id, Model model) {
        if (model.getAttribute(FIELD_PERSON) == null){
            if (id == null || id.isBlank()) {
                model.addAttribute(FIELD_PERSON, new PersonInput());
            } else {
                edit(model, id);
            }
        }

        return PEOPLE_FORM;
    }

    private void edit(Model model, String id) {
        PersonFound personFound = findPerson.byId(UUID.fromString(id));

        model.addAttribute(FIELD_PERSON, new PersonInput(personFound.id().toString(), personFound.name(),
                personFound.lastName(),
                personFound.birthDate(),
                personFound.email()));
    }

    @Override
    public String save(@ModelAttribute final PersonInput person, Model model) {

        try {
            if (person.getId() != null && !person.getId().isBlank()) {
                UUID id = UUID.fromString(person.getId());
                updatePerson.execute(id, new PersonSetUpdate(person.getName(),  person.getEmail(), person.getLastName(), person.getBirthDate()));
            } else {
                savePerson.execute(new PersonInsert(person.getName(), person.getLastName(), person.getBirthDate(), person.getEmail()));
            }
        } catch (ApplicationException e) {
            model.addAttribute(FIELD_PERSON, person);
            model.addAttribute("error", e.getMessage());
            return form(person.getId(), model);
        }

        return REDIRECT_PEOPLE;
    }

}
