package ru.halt.practice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.halt.practice.domain.Client;
import ru.halt.practice.domain.ClientToken;

/**
 * Created by Petr Rudenko on 19.02.2016.
 */
public interface ClientTokenRepository extends CrudRepository<ClientToken, Long> {
    ClientToken findByClientId(Long id);
    ClientToken findByClient(Client client);
    ClientToken findByToken(String token);
}
