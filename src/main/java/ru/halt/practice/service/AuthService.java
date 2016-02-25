package ru.halt.practice.service;

import org.springframework.stereotype.Service;
import ru.halt.practice.util.LoginUserInfo;
import ru.halt.practice.util.UserType;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
@Service
public class AuthService implements IAuthService {
//    @Autowired
//    private ClientRepository clientRepository;


    public boolean loginUser(LoginUserInfo userInfo){
        boolean result = true;
        if(userInfo.getUserType() == UserType.CLIENT){
            //Client client = clientRepository.findOne(userInfo.getId());
            //System.out.printf("Client: " + client.getFirstName());
            result = true;
        }
        if(userInfo.getUserType() == UserType.DRIVER){

        }
        if(userInfo.getUserType() == UserType.ARM_USER){

        }
        return result;
    }


}
