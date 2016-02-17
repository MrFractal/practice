package ru.halt.practice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Petr Rudenko on 25.01.2016.
 */
@Entity
@Table(name = "client")
public class Client extends AbstractEntity implements IUser {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Type(type = "yes_no")
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = false;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", insertable = true, updatable = true)
    @Fetch(value = FetchMode.JOIN)
    private Set<ClientRole> clientRoles = new HashSet<ClientRole>(0);

    public Client(){
    }

    public Client(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Set<ClientRole> getClientRoles() {
        return clientRoles;
    }

    public void setClientRoles(Set<ClientRole> clientRoles) {
        this.clientRoles = clientRoles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFirstName());
        sb.append(" ");
        sb.append(this.getLastName());
        return sb.toString();
    }

}
