package overengineer.projecthttp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RapazApplicationTests {

    @Test
    void contextLoads() {
        String message = "Context loaded successfully!";
        System.out.println(message);
        assertNotNull(message);
    }

}
