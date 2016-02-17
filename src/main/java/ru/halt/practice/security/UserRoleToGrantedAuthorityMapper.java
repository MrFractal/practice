package ru.halt.practice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;
import ru.halt.practice.domain.ClientRole;

import java.util.*;

/**
 * Created by Petr Rudenko on 11.02.2016.
 */
public class UserRoleToGrantedAuthorityMapper {
    private Collection<ClientRole> clientRoles;
    private Collection<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

    public UserRoleToGrantedAuthorityMapper(Collection userRoles){
        this.clientRoles = userRoles;
        buildGrantedAuthority();
    }

    private void buildGrantedAuthority() {
        if(!CollectionUtils.isEmpty(clientRoles)){
            for(ClientRole clientRole : clientRoles){
                grantedAuthorities.add(new SimpleGrantedAuthority(clientRole.getRoleName())); // UserGrantedAuthority(clientRole)
            }
        }
    }

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }
}
