import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Petr Rudenko on 12.02.2016.
 */
public class AdminControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final RestTemplate TEMPLATE = Utils.template();

    @Test
    public void test() throws Exception{
        LoginRequest request = new LoginRequest();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // fractal39:java - ZnJhY3RhbDM5OmphdmE=
        // fractal:java - ZnJhY3RhbDpqYXZh
        //headers.add("Authorization", "Basic ZnJhY3RhbDpqYXZh");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity response = restTemplate.exchange("http://localhost:8080/admin/test", HttpMethod.GET, entity, String.class);
        response.getStatusCode();
        response.getBody();
        response.getStatusCode();
        response.toString();
        //String response = TEMPLATE.postForObject("http://localhost:8080/admin/test", request, String.class, headers);
        //System.out.println("response: " + response);
    }
}
