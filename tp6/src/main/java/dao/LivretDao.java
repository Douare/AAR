package dao;

import modele.Livret;

import java.util.Collection;

public interface LivretDao {
    static Collection<Livret> findAll();
}
