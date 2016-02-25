package ru.halt.practice.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by Petr Rudenko on 19.02.2016.
 */
@Entity
@Table(name = "client_token")
public class ClientToken extends AbstractEntity {

    //@JoinColumn(name = "client_id", nullable = false, unique = true)

    @OneToOne
    private Client client;

    @Column(name = "token", nullable = false, unique = true, length = 200)
    private String token;

    @Column(name = "expire_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expireDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(DateTime expireDate) {
        this.expireDate = expireDate;
    }
}
