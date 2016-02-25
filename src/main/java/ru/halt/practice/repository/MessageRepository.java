package ru.halt.practice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import ru.halt.practice.domain.Message;

/**
 * Created by Petr Rudenko on 18.02.2016.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
    @PostAuthorize("returnObject?.client?.id == principal?.id")
    Message findOne(Long id);
}
