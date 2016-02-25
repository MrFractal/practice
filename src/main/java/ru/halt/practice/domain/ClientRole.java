package ru.halt.practice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Petr Rudenko on 11.02.2016.
 */
@Entity
@Table(name = "client_role", uniqueConstraints = @UniqueConstraint( columnNames = { "role_name", "client_id" }))
public class ClientRole extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "role_name", length = 45, nullable = false)
    private String roleName;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "["+ roleName +"]";
    }
}
