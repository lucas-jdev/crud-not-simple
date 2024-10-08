package restapi.rapaz.presentation.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * People is a frontend controller that handles the people page.
 */
@Controller
@RequestMapping("/pessoas")
public class Pessoas {

    @GetMapping
    public String pessoas() {
        return "pessoas";
    }

}
