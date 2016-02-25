package ru.halt.practice.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Petr Rudenko on 18.02.2016.
 */
@Entity
@Table(name = "message")
public class Message extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "from_client_id", nullable = false)
    private Client from;

    @NotNull
    @OneToOne
    @JoinColumn(name = "to_client_id")
    private Client to;

    @NotEmpty(message = "Message is required!")
    @Column(name="text", length = 255)
    private String text;

    public Client getFrom() {
        return from;
    }

    public void setFrom(Client from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Client getTo() {
        return to;
    }

    public void setTo(Client to) {
        this.to = to;
    }
}
