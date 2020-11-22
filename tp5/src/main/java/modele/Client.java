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


}
