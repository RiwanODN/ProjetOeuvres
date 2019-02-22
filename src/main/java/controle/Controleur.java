package controle;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.*;
import dao.Service;
import meserreurs.*;
import utilitaires.FonctionsUtiles;

/**
 * Servlet implementation class Controleur
 */
@WebServlet(name = "/ServletControleur" ,urlPatterns={"/ServletControleur"})
public class Controleur extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ACTION_TYPE = "action";
    private static final String LISTER_ADHERENT = "listerAdherent";
    private static final String LISTER_OEUVRE = "listerOeuvre";
    private static final String AJOUTER_ADHERENT = "ajouterAdherent";
    private static final String INSERER_ADHERENT = "insererAdherent";
    private static final String MODIFIER_ADHERENT = "modifierAdherent";
    private static final String MODIFIER_OEUVRE = "modifierOeuvre";
    private static final String LOGIN= "login";
    private static final String CONTROLELOGIN= "controleLogin";
    private static final String ERROR_KEY = "mesErreurs";
    private static final String ERROR_PAGE = "/erreur.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            processusTraiteRequete(request, response);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            processusTraiteRequete(request, response);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MonException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;

        if (LOGIN.equals(actionName)) {
            destinationPage = "/vues/formLogin.jsp";
        }
        else
            if ( CONTROLELOGIN.equals(actionName))
            {
                String login = request.getParameter("login");
                String pwd = request.getParameter("pwd");
                String message ="";
                try {

                    Service unService = new Service();
                    Utilisateur unUtilisateur = unService.getUtilistateur(login);
                    if (unUtilisateur != null) {
                        try {
                            String pwdmd5 = FonctionsUtiles.md5(pwd);
                            if (unUtilisateur.getMotPasse().equals(pwdmd5)) {
                                HttpSession session = request.getSession();
                                session.setAttribute("id", unUtilisateur.getNumUtil());
                                destinationPage = "/index.jsp";
                            } else {
                                message = "mot de passe erroné";
                                request.setAttribute("message", message);
                                destinationPage = "/vues/formLogin.jsp";
                            }
                        } catch (NoSuchAlgorithmException e) {
                            request.setAttribute("MesErreurs", e.getMessage());
                            destinationPage = "/vues/Erreur.jsp";
                        }
                    } else {
                        message = "login erroné";
                        request.setAttribute("message", message);
                        destinationPage = "/vues/formLogin.jsp";
                    }
                } catch (MonException e) {
                    request.setAttribute("MesErreurs", e.getMessage());
                    destinationPage = "/vues/Erreur.jsp";
                }
            }
         else
        // execute l'action
        if (LISTER_ADHERENT.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("mesAdherents", unService.consulterListeAdherents());
                destinationPage = "/vues/listerAdherent.jsp";
            } catch (MonException e) {
                // TODO Auto-generated catch block
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }
        } else if (LISTER_OEUVRE.equals(actionName)) {
            try {

                Service unService = new Service();
                request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());
                destinationPage = "/vues/listerOeuvre.jsp";
            } catch (MonException e) {
                // TODO Auto-generated catch block
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }
        }

        else
        if (AJOUTER_ADHERENT.equals(actionName)) {

            destinationPage = "/vues/ajouterAdherent.jsp";
        } else if (INSERER_ADHERENT.equals(actionName)) {
            try {
                Adherent unAdherent = new Adherent();
                unAdherent.setNomAdherent(request.getParameter("txtnom"));
                unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
                unAdherent.setVilleAdherent(request.getParameter("txtville"));
                Service unService = new Service();
                unService.insertAdherent(unAdherent);
                destinationPage = "/index.jsp";
            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }

        }

        else
        if (MODIFIER_ADHERENT.equals(actionName)) {
            Adherent unAdherent = new Adherent();
            Service unService = new Service();
            unService.consulterAdherent(unAdherent.getIdAdherent());
            //request.setAttribute()
            destinationPage = "/vues/modifierAdherent.jsp";

        }

        else if (MODIFIER_OEUVRE.equals(actionName)) {
            try {
                String idOeuvre = request.getParameter("id");
                Service unService = new Service();
                Oeuvrevente oeuvre = unService.consulterOeuvre(Integer.parseInt(idOeuvre));
                List<Proprietaire> props = unService.consulterListeProprietaires();
                request.setAttribute("oeuvre", oeuvre);
                request.setAttribute("props", props);
                destinationPage = "/vues/modifierOeuvre.jsp";
            }  catch (MonException e) {
            // TODO Auto-generated catch block
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur.jsp";
        }


    } else if (MODIFIER_ADHERENT.equals(actionName)) {
           /*try {
                Adherent unAdherent = new Adherent();
                unAdherent.setNomAdherent(request.getParameter("txtnom"));
                unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
                unAdherent.setVilleAdherent(request.getParameter("txtville"));
                Service unService = new Service();
                unService.insertAdherent(unAdherent);
                destinationPage = "/index.jsp";
            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur.jsp";
            }*/

        }

        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
            destinationPage = "/vues/Erreur.jsp";
        }
        // Redirection vers la page jsp appropriee
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);

    }



}
