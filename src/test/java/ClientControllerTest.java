import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

import java.util.Arrays;
import java.util.List;

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

        RestResponse<List<Client>> response = TEMPLATE.postForObject("http://localhost:8080/client/list", request, RestResponse.class);
        response.getData();
        response.getTotalRows();
    }


    @Test
    public void testMessage() throws Exception {
        PageSearch request = new PageSearch();
        String asString = OBJECT_MAPPER.writeValueAsString(request);
        RestResponse response = TEMPLATE.postForObject("http://localhost:8080/client/messages", request, RestResponse.class);
        response.getData();
        response.getPage();
    }

    @Test
    public void testGetById() throws Exception{
        PageSearch request = new PageSearch();
        request.setId(5L);
        String asString = OBJECT_MAPPER.writeValueAsString(request);
        String clientInfo  = TEMPLATE.postForObject("http://localhost:8080/client/id", request, String.class);
        String str = clientInfo;
    }


    @Test
    public void testLogin() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setLogin("fractalll");
        request.setPassword("java");

        String asString = OBJECT_MAPPER.writeValueAsString(request);
        LoginResponse response = TEMPLATE.postForObject("http://localhost:8080/client/login", request, LoginResponse.class);
        String asStringResponse = OBJECT_MAPPER.writeValueAsString(response);
        response.getToken();
        response.toString();
    }

    private LoginUserInfo buildLoginUserInfo() {
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setId(1L);
        userInfo.setLogin("fractalaaa");
        userInfo.setPassword("java");
        return userInfo;
    }
}
