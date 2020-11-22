package demo;

import modele.Client;
import modele.Compte;
import modele.Livret;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    /**
     * TERMINAL
     *
     * docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:5
     * docker exec -it mysql mysql -u root -p
     * CREATE DATABASE banque;
     * SELECT * FROM COMPTE;
     * SELECT * FROM CLIENT;
     *
     *
     */
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banquePU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction entityTransaction = em.getTransaction();
        Client juliette = new Client("AAA","AAA","AAAA");

        Compte compte = new Compte(juliette, 222.0, new Date());
        Livret livret = new Livret(compte.getTitulaire(), compte.getSolde(), compte.getDateOuverture(), 0.75);
        juliette.addCompte(livret);
        juliette.addCompte(compte);

        em.getTransaction().begin();
        em.persist(juliette);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}