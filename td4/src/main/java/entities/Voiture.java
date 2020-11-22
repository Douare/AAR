package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Voiture {
    @Id
    private String im;
    private String surnom;
    //Charge systématiquement la liste des passagers -> dangereux si la liste est longue
    // /!\ pas de Eager en symétrique ou en cascade
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "v")
    private Collection<Personne> passagers;

    public Voiture(String im, String surnom) {
        this();
        this.im = im;
        this.surnom = surnom;
    }

    public Voiture() {
        passagers = new ArrayList<>();
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public Collection<Personne> getPassagers() {
        return passagers;
    }

    public void setPassagers(Collection<Personne> passagers) {
        this.passagers = passagers;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "im='" + im + '\'' +
                ", surnom='" + surnom + '\'' +
                ", passagers=" + passagers +
                '}';
    }
}
