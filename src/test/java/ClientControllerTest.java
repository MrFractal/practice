import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import ru.halt.practice.domain.Client;
import ru.halt.practice.rest.ClientInfo;
import ru.halt.practice.rest.LoginRequest;
import ru.halt.practice.rest.LoginResponse;
import ru.halt.practice.rest.PageSearch;
import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.RestResponse;
import ru.halt.practice.util.UserType;
import ru.halt.practice.util.Utils;

/**
 * Created by Petr Rudenko on 31.01.2016.
 */
public class ClientControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final RestTemplate TEMPLATE = Utils.template();

    @Test
    public void testListClient() throws Exception {
        PageSearch request = new PageSearch();
        //request.setId(1L);
        request.setSize(10);
        //request.setSidx("firstName");
        request.setSord("desc");
        request.setPage(1);
        String asString = OBJECT_MAPPER.writeValueAsString(request);
        RestResponse response = TEMPLATE.postForObject("http://localhost:8080/client/list", request, RestResponse.class);
        response.getData();
        response.getTotalRows();
    }




    @Test
    public void testGetById() throws Exception{
        PageSearch request = new PageSearch();
        request.setId(1L);
        String asString = OBJECT_MAPPER.writeValueAsString(request);
        ClientInfo client = TEMPLATE.postForObject("http://localhost:8080/client/id", request, ClientInfo.class);
        client.getLogin();
        client.getPassword();
    }


    @Test
    public void testLogin() throws Exception{
        LoginRequest request = new LoginRequest();
        request.setLoginUserInfo(buildLoginUserInfo());
        String asString = OBJECT_MAPPER.writeValueAsString(request);
        LoginResponse response = TEMPLATE.postForObject("http://localhost:8080/client/login", request, LoginResponse.class);
        response.getStr();
    }

    private LoginUserInfo buildLoginUserInfo() {
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setId(1L);
        userInfo.setUserType(UserType.DRIVER);
        return userInfo;
    }
}
