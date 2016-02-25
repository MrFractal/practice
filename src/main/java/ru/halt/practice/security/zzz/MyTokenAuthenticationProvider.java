package ru.halt.practice.security.zzz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.halt.practice.domain.ClientToken;
import ru.halt.practice.repository.ClientTokenRepository;
import ru.halt.practice.security.CurrentUser;
import ru.halt.practice.security.UserRoleToGrantedAuthorityMapper;

import java.util.*;

/**
 * Created by Petr Rudenko on 23.02.2016.
 */

public class MyTokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private ClientTokenRepository clientTokenRepository;


    private Map<String, Authentication> tokenHandler = new HashMap<String, Authentication>();

    public MyTokenAuthenticationProvider(){
    }

    public void store(String token, Authentication authentication) {
        tokenHandler.put(token, authentication); //
    }


    //@Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        String token = (String)auth.getPrincipal();

        System.out.println("In MyTokenAuthenticationProvider, token ------------ = " + token);

        if (StringUtils.isEmpty(token)) {
            throw new BadCredentialsException("Invalid token");
        }

        ClientToken clientToken = clientTokenRepository.findByToken(token);
        if(clientToken == null){
            throw new UsernameNotFoundException("User by token not found");
        }
        CurrentUser currentUser = new CurrentUser(clientToken.getClient().getId(), clientToken.getClient().getLogin(), clientToken.getClient().getPassword(), clientToken.getClient().isEnabled(), new UserRoleToGrantedAuthorityMapper(clientToken.getClient().getClientRoles()).getGrantedAuthorities());
        if(!currentUser.isEnabled()){
            throw new UsernameNotFoundException("User is disabled");
        }

        PreAuthenticatedAuthenticationToken authRequest = new PreAuthenticatedAuthenticationToken(clientToken.getClient().getLogin(), clientToken.getClient().getPassword(), new UserRoleToGrantedAuthorityMapper(clientToken.getClient().getClientRoles()).getGrantedAuthorities());
        authRequest.setDetails(token);

        // Don't let spring decide.. you already have made the right decisions. Tell spring you have an authenticated user.
        SecurityContextHolder.getContext().setAuthentication(authRequest);

        /*
        if (!tokenHandler.containsKey(token)) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        */

        return authRequest;
    }

    //@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
