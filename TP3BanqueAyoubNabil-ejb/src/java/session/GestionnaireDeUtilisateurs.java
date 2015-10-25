/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nabil
 */
@Stateless
public class GestionnaireDeUtilisateurs{
    
    @PersistenceContext(unitName = "TP3BanqueAyoubNabil-ejbPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Utilisateur u;
    
    public void creerUtilisateurTest(){
        creerUtilisateurs(new Utilisateur("admin", "admin", "nom1", "prenom1"));
    }
    private void creerUtilisateurs(Utilisateur u) {
        em.persist(u);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Utilisateur getUser(){
        return u;
    }
    
    public boolean checkIdentificationOk(String login, String password){
        String r = "select u from Utilisateur u where u.login=:login";
        System.out.println("checkIdentificationOk ------->  login =" + login + "  password = "
                + password + ": requete " + r);
        
        try {
        Query q = em.createQuery(r);
        q.setParameter("login", login);
         u = (Utilisateur) q.getSingleResult();
         if(u.getPassword().equals(password)) {
             // bon utilisateur et bon mot de passe
             System.out.println("AUTHENTIFICATION OK");
             return true;
         } else {
             // bon utilisateur, mauvais mot de passe
             System.out.println("UTILISATEUR OK MAIS MAUVAIS MDP");
             return false;
         }
        } catch(NoResultException e) {
            // utilisateur non reconnu
            System.out.println("UTILISATEUR NON TROUVE");
         return false;
        }
    } 
    
}
