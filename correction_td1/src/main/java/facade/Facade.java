package facade;

import modele.Competence;
import modele.CompetenceMembre;
import modele.Membre;
import modele.Projet;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class Facade {
    private Collection<Membre> membres;
    private Collection<Projet> projets;
    private Collection<Competence> competences;

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

    public void initDB(){
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



    public Collection<Projet> competences(Membre m) {
        // On initialise les projets : tous les projets moins ceux auquel m participe
        Collection<Projet> pcomp=new ArrayList<>();
        pcomp.addAll(projets);
        pcomp.removeAll(m.getResponsable());
        pcomp.removeAll(m.getParticipe());

        // les competences de m
        Collection<Competence> compm=new ArrayList<>();
        for (CompetenceMembre cm:m.getDeclare()) {
            compm.add(cm.getDeType());
        }

        Collection<Projet> res=new ArrayList<>();

        // on ne garde que les projets avec des competences de m
        for (Projet p:pcomp) {
            for (Competence comp:p.getNecessite()) {
                if (compm.contains(comp)) {
                    res.add(p);
                    break;
                }
            }
        }
        return res;
    }

    public Collection<Projet> autres(Membre m, Collection<Projet> hascomp) {
        Collection<Projet> pautres=new ArrayList<>();
        pautres.addAll(projets);
        pautres.removeAll(m.getResponsable());
        pautres.removeAll(m.getParticipe());
        pautres.removeAll(hascomp);
        return pautres;
    }

    public Membre getMembreFromLogin(String login) {
        for (Membre m:membres) {
            if (m.getLogin().equals(login)) {
                return m;
            }
        }
        return null;
    }

    public Projet getProjetFromIntituleP(String intituleP) {
        for (Projet p:projets) {
            if (p.getIntituleP().equals(intituleP)) {
                return p;
            }
        }
        return null;
    }

    public Membre logging(String log, String mdp){
        for (Membre m : membres) {
            if (m.getLogin().equals(log) && m.getMotdepasse().equals(mdp)) {
                return m;
            }
        }
        return null;
    }
}

