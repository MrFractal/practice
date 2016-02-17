import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.util.Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Petr Rudenko on 12.02.2016.
 */
public class AdminControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final RestTemplate TEMPLATE = Utils.template();

    @Test
    public void test() throws Exception{
        LoginRequest request = new LoginRequest();
        String response = TEMPLATE.postForObject("http://localhost:8080/admin/test", request, String.class);
        System.out.println("response: " + response);
        //response.getStr();
    }
}
