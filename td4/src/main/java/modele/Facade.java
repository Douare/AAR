package modele;

import entities.Personne;
import entities.Voiture;
//import jdk.jfr.Percentage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class Facade {
    @PersistenceContext
    EntityManager em;

    private Collection<Membre> membres;
    private Collection<Projet> projets;
    private Collection<Competence> competences;

    public void lesVoitures(){
        Query q = em.createQuery("from Voiture v");
        List<Voiture> voitures =  q.getResultList();
        for (Voiture voiture: voitures){
            System.out.println(voiture);
        }
    }

    @Transactional
    public void nouvelleVoiture(){
        Voiture v = em.find(Voiture.class, "KINTOU");
        Personne p = new Personne();
        p.setNom("Goku");
        em.persist(p);
        Collection<Personne> passagers = v.getPassagers();
        passagers.add(p);
        v.setPassagers(passagers);
    }

    public Facade() {
        membres=new ArrayList<>();
        Membre matthieu=new Membre("Matthieu","Matthieu","Matthieu");
        membres.add(matthieu);
        Membre fred=new Membre("Fred","Fred","Fred");
        membres.add(fred);

        projets=new ArrayList<>();
        Projet allanParson=new Projet("Allan Parson","Un projet musical");
        allanParson.setDirigePar(matthieu);
        matthieu.getResponsable().add(allanParson);
        projets.add(allanParson);
        Projet xion=new Projet("Xion","Pour partager une vision");
        xion.setDirigePar(fred);
        fred.getResponsable().add(xion);
        projets.add(xion);

        competences = new ArrayList<>();
        Competence java=new Competence("Java","POO");
        competences.add(java);
        Competence management=new Competence("Management", "Gestion d'équipe, résolution de conflits");
        competences.add(management);

        CompetenceMembre javamatth=new CompetenceMembre(matthieu,java,3,"java avancé");
        matthieu.getDeclare().add(javamatth);
        java.getCompMembre().add(javamatth);
        CompetenceMembre manafred=new CompetenceMembre(fred,management,4,"tout est sous contrôle");
        fred.getDeclare().add(manafred);
        management.getCompMembre().add(manafred);


        allanParson.getNecessite().addAll(competences);
        xion.getNecessite().addAll(competences);
        java.getRequisePour().addAll(projets);
        management.getRequisePour().addAll(projets);

    }

    public Collection<Membre> getMembres() {
        return membres;
    }

    public void setMembres(Collection<Membre> membres) {
        this.membres = membres;
    }

    public Collection<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Collection<Projet> projets) {
        this.projets = projets;
    }

    public Collection<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Collection<Competence> competences) {
        this.competences = competences;
    }


    public Membre testLM(String log, String mdp) {
        for (Membre m : membres) {
            if (m.getLogin().equals(log) &&
                    m.getMotdepasse().equals(mdp)) {
                return m;
            }
        }
        return null;
    }




}
