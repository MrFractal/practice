package ru.halt.practice.service;

/**
 * Created by Petr Rudenko on 19.02.2016.
 */

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.halt.practice.domain.Client;
import ru.halt.practice.domain.ClientToken;
import ru.halt.practice.exception.UserNotFound;
import ru.halt.practice.repository.ClientRepository;
import ru.halt.practice.repository.ClientTokenRepository;
import ru.halt.practice.util.TokenUtil;


@Service
@Transactional
public class ClientTokenService {
    @Autowired
    private ClientTokenRepository clientTokenRepository;
    @Autowired
    private ClientRepository clientRepository;


    public String generateToken(Long id) {
        Client client = null;
        ClientToken clientToken = clientTokenRepository.findByClientId(id);
        if(clientToken == null){
            client = clientRepository.findOne(id);
            if(client == null) {
                throw new UserNotFound();
            }
            clientToken.setClient(client);
        }
        clientToken.setToken(TokenUtil.generateToken(client.getLogin(), client.getPassword()));
        clientToken.setExpireDate(DateTime.now().plusDays(1));
        clientTokenRepository.save(clientToken);
        return clientToken.getToken();
    }


    public boolean validateToken(ClientToken byToken) {
        //ClientToken byToken = clientTokenRepository.findByToken(token);
        return (byToken == null) ? false : byToken.getExpireDate().isAfterNow();
    }

    public Client getUserByToken(String token) {
        ClientToken byToken = clientTokenRepository.findByToken(token);
        return (byToken == null) ? null : byToken.getClient();
    }
}
