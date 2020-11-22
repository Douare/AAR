package servlets;

import facade.Facade;
import modele.Competence;
import modele.CompetenceMembre;
import modele.Membre;
import modele.Projet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

@WebServlet(name = "Controleur", urlPatterns = "/")
public class Controleur extends HttpServlet {

    @Autowired
    public Facade facade;

    @Override
    public void init() throws ServletException {
        facade.initDB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo = request.getParameter("TODO");

        if ((request.getSession().getAttribute("util")==null) && ((todo == null) || (todo.equals("selogguer")==false))) {
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").
                    forward(request, response);
        } else {
            if (todo == null) {
                menu(request, response);

            } else {
                switch (todo) {
                    case "selogguer":
                        String log = request.getParameter("login");
                        String mdp = request.getParameter("mdp");
                        Membre m = facade.logging(log,mdp);
                                // le login est mis en session pour s'en souvenir...
                        if (m!=null){
                            request.getSession().setAttribute("util", m.getLogin());
                            menu(request, response);
                        }

                        break;

                    case "participer":
                        Membre membre = facade.getMembreFromLogin((String) request.getSession().getAttribute("util"));
                        Projet projet = facade.getProjetFromIntituleP(request.getParameter("intituleP"));
                        if ((projet.getContributionDe().contains(membre) == false) && (projet.getDirigePar() != membre)) {
                            projet.getContributionDe().add(membre);
                            membre.getParticipe().add(projet);
                        }
                        menu(request, response);

                        break;

                    default:
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
                }
            }
        }
    }

    private void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Membre membre= facade.getMembreFromLogin((String)request.getSession().getAttribute("util"));

        // on affiche la page de menu, en lui passant le membre qui vient de se logguer
        request.setAttribute("courant",membre);

        Collection<Projet> pcomp=facade.competences(membre);
        request.setAttribute("competent",pcomp);
        request.setAttribute("autres",facade.autres(membre,pcomp));

        request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request,response);
    }

}










