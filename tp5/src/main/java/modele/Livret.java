package modele;


import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Livret extends Compte{
    private double tauxInteret;


    public Livret(){
    }

    public Livret(Client titulaire, double solde, Date date, double tauxInteret) {
        super(titulaire,solde,date);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
