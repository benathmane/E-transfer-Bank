/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Utilisateur;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.GestionnaireDeUtilisateurs;

/**
 *
 * @author nabil
 */
@Named(value = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {
    @EJB
    private GestionnaireDeUtilisateurs gu;

    private String login;
    private String password;
    private Utilisateur user;
    private boolean connected = false;
    private String message = "Veuillez vous identifier :";
    private String messageErrorLog;

    public String getMessageErrorLog() {
        return messageErrorLog;
    }

    public void setMessageErrorLog(String messageErrorLog) {
        this.messageErrorLog = messageErrorLog;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
   
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
         System.out.println("GET LOGIN ");
        return login;
    }

    public void setLogin(String login) {
        System.out.println("SET LOGIN " + login);
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

   

    /** Creates a new instance of LoginMBean */
    public LoginMBean() {
    }

    public String deconnexion() {
        connected = false;
        message = "Veuillez vous identifier :";
        messageErrorLog = "";
        return "/Login.xhtml";
    }

    public String checkLogin() {
        System.out.println("CHECK LOGIN");
        // Normalement à partir du login on devrait demander à l'ejb
        // gestionnaireUtilisateur de chercher dans la BD et retourner un 
        // utilisateur qu'on met dans la propriété utilisateur.
        // Là on simule...
        connected = gu.checkIdentificationOk(login, password);
        String path;
        if(connected) {
            System.out.println("IDENTIFICATION OK");
            message = "Bienvenue, vous êtes connecté en tant que " + login + " ! ";
            user = gu.getUser();
            path = "Profil.xhtml?faces-redirect=true";
        } else {
            System.out.println("IDENTIFICATION PAS OK");
              messageErrorLog = "Mauvais login/password, veuillez recommencer !";
             System.out.println(message);
              path = "Login.xhtml?faces-redirect=true";
        }
        return path;
        
    }
//    public void checkLogin() {
//        System.out.println("CHECK LOGIN");
//        // Normalement à partir du login on devrait demander à l'ejb
//        // gestionnaireUtilisateur de chercher dans la BD et retourner un 
//        // utilisateur qu'on met dans la propriété utilisateur.
//        // Là on simule...
//        connected = gu.checkIdentificationOk(login, password);
//
//        if (connected) {
//            System.out.println("IDENTIFICATION OK");
//            message = "Bienvenue, vous êtes connecté en tant que " + login + " ! ";
//        } else {
//            System.out.println("IDENTIFICATION PAS OK");
//            message = "Mauvais login/password, veuillez recommencer !";
//        }
//
//    }
    
}
