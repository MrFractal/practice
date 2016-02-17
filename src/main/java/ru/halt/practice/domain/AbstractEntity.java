package ru.halt.practice.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import ru.halt.practice.util.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Petr Rudenko on 09.02.2016.
 */
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "last_update")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastUpdate;

    @Version
    @Column(name = "version", nullable = false)
    private int version = 0;

    @PreUpdate
    protected void onPreUpdate() {
        lastUpdate = new DateTime();
    }

    public DateTime getLastUpdate() {
        return lastUpdate;
    }

    public int getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }
}
