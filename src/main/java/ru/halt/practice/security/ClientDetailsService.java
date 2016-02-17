package ru.halt.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.halt.practice.domain.Client;
import ru.halt.practice.repository.ClientRepository;

/**
 * Created by Petr Rudenko on 10.02.2016.
 */
@Service("clientDetailsService")
public class ClientDetailsService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;
    private User user;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        System.err.println("In ClientDetailsService: user name = " + login);

        Client clientByLogin = clientRepository.findByLogin(login);
        if(clientByLogin == null){
            throw new UsernameNotFoundException("");
        }
        user = new User(clientByLogin.getLogin(), clientByLogin.getPassword(), clientByLogin.isEnabled(), true, true, true, new UserRoleToGrantedAuthorityMapper(clientByLogin.getClientRoles()).getGrantedAuthorities());
        return user;
    }

    public User getUser() {
        return user;
    }
}
