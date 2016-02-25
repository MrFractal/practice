package ru.halt.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.halt.practice.domain.Client;
import ru.halt.practice.domain.ClientToken;
import ru.halt.practice.repository.ClientRepository;
import ru.halt.practice.repository.ClientTokenRepository;
import ru.halt.practice.service.ClientTokenService;

import javax.naming.AuthenticationException;

/**
 * Created by Petr Rudenko on 10.02.2016.
 */

@Service("clientDetailsService")
public class ClientDetailsService implements UserDetailsService {
    @Autowired
    private ClientTokenService clientTokenService;
    @Autowired
    private ClientTokenRepository clientTokenRepository;

    private CurrentUser currentUser;
    //private User user;

    @Transactional
    public UserDetails loadUserByUsername(String token) {

        System.out.println("DetailsService: user token = " + token);

        //Client clientByLogin = clientRepository.findByLogin(login);
        ClientToken clientByToken = clientTokenRepository.findByToken(token);

        if(clientByToken == null) {
            System.out.println("clientByToken == null ~~~~~~~~~~~~");
            throw new UsernameNotFoundException("User by token: " + token +", not found");
        }

        if(!clientTokenService.validateToken(clientByToken)){
            System.out.println("Token expired");
            throw new UsernameNotFoundException("Token expired");
        }

        currentUser = new CurrentUser(clientByToken.getClient().getId(), clientByToken.getClient().getLogin(), clientByToken.getClient().getPassword(), clientByToken.getClient().isEnabled(), new UserRoleToGrantedAuthorityMapper(clientByToken.getClient().getClientRoles()).getGrantedAuthorities());
        System.out.println("currentUser is enabled: " + currentUser.isEnabled());
        if(!currentUser.isEnabled()){
            throw new UsernameNotFoundException("User is disabled");
        }

        //currentUser = new CurrentUser(clientByLogin.getId(), clientByLogin.getLogin(), clientByLogin.getPassword(), clientByLogin.isEnabled(), new UserRoleToGrantedAuthorityMapper(clientByLogin.getClientRoles()).getGrantedAuthorities());
        //user = new User(clientByLogin.getLogin(), clientByLogin.getPassword(), clientByLogin.isEnabled(), true, true, true, new UserRoleToGrantedAuthorityMapper(clientByLogin.getClientRoles()).getGrantedAuthorities());
        return currentUser;
    }

}


