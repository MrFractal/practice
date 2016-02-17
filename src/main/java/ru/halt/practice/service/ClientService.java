package ru.halt.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.halt.practice.domain.Client;
import ru.halt.practice.domain.ClientRole;
import ru.halt.practice.exception.UserNotFound;
import ru.halt.practice.repository.ClientRepository;
import ru.halt.practice.rest.ClientInfo;
import ru.halt.practice.rest.PageSearch;
import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.ModelUtil;
import ru.halt.practice.util.RestResponse;
import ru.halt.practice.util.RoleUser;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;


/**
 * Created by Petr Rudenko on 25.01.2016.
 */
@Service
@Transactional
public class ClientService implements IClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.messages", Locale.getDefault());
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Environment env;


    public void login(LoginUserInfo userInfo) {
        Client client = clientRepository.findByLoginAndPassword(userInfo.getLogin(), userInfo.getPassword());
        if(client == null) {
            throw new UserNotFound();
        }
    }

    public void create(){
        clientRepository.save(new Client("Petro", "Rudenko", "fractal" + new Random().nextInt(100), "java"));
    }

    public void update(Long id) {
        throw new UserNotFound(); // BUNDLE.getString("userNotFound")
        //Client client = clientRepository.findOne(id);
        //System.out.println("client = " + client);
        //if(client == null){
        //    throw new UserNotFound(BUNDLE.getString("userNotFound"));
        //}
    }

    public void delete() {

    }


    public Client getById(Long id){
        Client client = clientRepository.findOne(id);
        Set<ClientRole> roles = client.getClientRoles();
        for(ClientRole role: roles){
            LOGGER.info("role: " + role.getRoleName());
        }
        return client;
    }


    public RestResponse list(PageSearch filter){
        RestResponse response = new RestResponse();
        PageRequest pageRequest = new PageRequest(filter.getPage() - 1, filter.getSize(), Sort.Direction.valueOf(filter.getSord().toUpperCase()), filter.getSidx());
        Page<Client> result;
        if(StringUtils.hasText(filter.getSearch())){
            result = clientRepository.findByFirstNameOrLastName(filter.getSearch(), pageRequest);
        } else {
            if(filter.getId() != null){
                result = clientRepository.findById(filter.getId(), pageRequest);
            } else{
                result = clientRepository.findAll(pageRequest);
            }
        }
        response.setData(result.getContent());
        response.setTotalRows(result.getTotalElements());
        response.setPage(filter.getPage());
        return response;
    }

    public void test() {
        LOGGER.info("xxxxxx. From Env: " + env.getProperty("jdbc.driverClassName"));
        if(true){
            throw new NullPointerException("xxxxxx. From Env: " + env.getProperty("jdbc.driverClassName"));
        }
    }
}
