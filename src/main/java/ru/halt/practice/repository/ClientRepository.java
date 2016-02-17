package ru.halt.practice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.halt.practice.domain.Client;

/**
 * Created by Petr Rudenko on 30.01.2016.
 */
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Page<Client> findByFirstNameOrLastName(String search, Pageable pageable);
    Page<Client> findById(Long id, Pageable pageable);
    Client findByLoginAndPassword(String login, String password);
    Client findByLogin(String login);
}
