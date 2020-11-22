package dao;

import modele.Compte;

import java.util.Collection;

public interface CompteDao {
    static Collection<Compte> find(long idClient);

    static Collection<Compte> findAll();
}
