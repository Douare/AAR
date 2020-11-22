package modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String nom;
    private String prenom;
    private String adresse;

    @OneToMany(mappedBy = "titulaire", cascade = CascadeType.PERSIST)
    private Collection<Compte> comptes;

    public Client() {
        comptes = new ArrayList<>();
    }
    public Client(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.comptes = new ArrayList<>();
    }

    public void addCompte(Compte compte) {
        this.comptes.add(compte);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }
}
