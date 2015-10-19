/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tp2.CompteBancaire;

/**
 *
 * @author BENATHMANE
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {
    @PersistenceContext(unitName = "TP3BanqueAyoubNabil-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public GestionnaireDeCompteBancaire(){
        
        
    }
    public GestionnaireDeCompteBancaire(String nom, int solde){
        
        
    }
    
    public void creerComptesTest() {  
        creerCompte(new CompteBancaire("BENATHMANE Ayoub", 15094000));  
        creerCompte(new CompteBancaire("AALALOU	Soufiane", 950000));  
        creerCompte(new CompteBancaire("ALI KARI Zalbiya", 20000));  
        creerCompte(new CompteBancaire("BEATINI Loric", 200000));  
        creerCompte(new CompteBancaire("BOUGHZAF Nabil", 1580000));  
        creerCompte(new CompteBancaire("COMBA Florent", 109800));  
        creerCompte(new CompteBancaire("DIALLO Hassatou", 1002010));  
        creerCompte(new CompteBancaire("GARRO Kevin", 1085520));  
        creerCompte(new CompteBancaire("GAUCHE Nicolas", 151500));  
        creerCompte(new CompteBancaire("GHALEM Sara", 2302100));  
        creerCompte(new CompteBancaire("JAUVAT Fabrice", 5620000));  
        creerCompte(new CompteBancaire("JMYI Zakarya", 100552));  
        creerCompte(new CompteBancaire("KORFED Elmahdi", 362000));  
        creerCompte(new CompteBancaire("LINARES	Thibaut", 230000));  
        creerCompte(new CompteBancaire("MASSA Florian", 694000));  
        creerCompte(new CompteBancaire("MEBARKIA Lotfi", 875000));  
        creerCompte(new CompteBancaire("MOISE Yoann", 5620000));  
        creerCompte(new CompteBancaire("PONCET Yoann", 963500));  
        creerCompte(new CompteBancaire("ROTTIER	Gaël", 195630));  
        creerCompte(new CompteBancaire("SAIDI Marouane", 185900)); 
        creerCompte(new CompteBancaire("SIMO DJILO Zacharie", 100852));  
    }  

    private void creerCompte(String nom, int solde) {
        CompteBancaire cb = new CompteBancaire(nom, solde);
        em.persist(cb);
    }
    
    private void creerCompte(CompteBancaire c) {
        em.persist(c);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<CompteBancaire> findAll() {
        Query q= em.createNamedQuery("CompteBancaire.findAll");
        return q.getResultList();
    }

    public void crediterCompte(int id, int montant) {
        // On va chercher un compte dans la base, il est connecté
        CompteBancaire c = em.find(CompteBancaire.class, id);
        // On update juste en faisant solde+=montant
        c.deposer(montant);
    }
    
    public void debiterCompte(int id, int montant) {
        CompteBancaire c = em.find(CompteBancaire.class, id);
        c.retirer(montant);
    }
    private int idCompteADebiter;

    /**
     * Get the value of idCompteADebiter
     *
     * @return the value of idCompteADebiter
     */
    public int getIdCompteADebiter() {
        return idCompteADebiter;
    }

    /**
     * Set the value of idCompteADebiter
     *
     * @param idCompteADebiter new value of idCompteADebiter
     */
    public void setIdCompteADebiter(int idCompteADebiter) {
        this.idCompteADebiter = idCompteADebiter;
    }

        public void transferer(int id1, int id2, int montant) {
        debiterCompte(id1, montant);
        crediterCompte(id2, montant);
    }
  
}