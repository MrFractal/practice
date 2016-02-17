import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.UserType;
import ru.halt.practice.util.Utils;

/**
 * Created by Petr Rudenko on 12.02.2016.
 */
public class AuthControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final RestTemplate TEMPLATE = Utils.template();


    @Test
    public void testLogin() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setLoginUserInfo(buildLoginUserInfo());
        String asString = OBJECT_MAPPER.writeValueAsString(request);
        LoginResponse response = TEMPLATE.postForObject("http://localhost:8080/auth/login", request, LoginResponse.class);
        response.getStr();
    }

    private LoginUserInfo buildLoginUserInfo() {
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setId(1L);
        userInfo.setUserType(UserType.CLIENT);
        userInfo.setLogin("fractal");
        userInfo.setPassword("jjjjj");
        return userInfo;
    }
}
