package dao;

import modele.Client;

import java.util.Collection;

public interface ClientDao {
    static Client find(long id);

    static Collection<Client> findAll();

    void create(Client c);
}
