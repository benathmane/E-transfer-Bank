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
import entities.CompteBancaire;
import entities.CompteCourant;
import entities.CompteEpargne;
import entities.OperationBancaire;
import java.util.Map;
import java.util.Map.Entry;

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
    public GestionnaireDeCompteBancaire() {

    }

    public GestionnaireDeCompteBancaire(String nom, int solde) {

    }

    public void creerComptesTest() {
        /*creerCompte(new CompteBancaire("BENATHMANE Ayoub", 15094000));
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
         creerCompte(new CompteBancaire("SIMO DJILO Zacharie", 100852));*/
        for (int i = 0; i < 200; i++) {
            String nom = "CR Proprio" + i;
            int solde = (int) Math.round(Math.random() * 100000);
            creerCompte(new CompteCourant(nom, solde));
        }
        for (int i = 0; i < 200; i++) {
            String nom = "CE Proprio" + i;
            int solde = (int) Math.round(Math.random() * 100000);
            double taux = 1 + 5 * Math.random();
            creerCompte(new CompteEpargne(nom, solde, taux));
        }
    }

    private void creerCompte(String nom, int solde) {
        CompteBancaire cb = new CompteCourant(nom, solde);
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
        Query q = em.createNamedQuery("CompteBancaire.findAll");
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

    public List<OperationBancaire> getOperationsBancaires(int compteId) {
        CompteBancaire c = em.find(CompteBancaire.class, compteId);
        return c.getListeOprerations();
    }

    public void supprimerCompte(CompteBancaire c) {
        em.remove(em.merge(c));
    }

    public List<CompteBancaire> getComptes(int start, int nb) {
        Query q = em.createNamedQuery("CompteBancaire.findAll");
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }

    public long getNombreDeComptes() {
        Query q = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c");
        return (long) q.getSingleResult();
    }

    public List<CompteBancaire> getComptesTriesParNom(int start, int nb, String order) {
        String orderValue = "";
        if (order.equals("ASCENDING")) {
            orderValue = "ASC";
        } else {
            orderValue = "DESC";
        }
        String r = "select c from CompteBancaire c order by c.nom "
                + orderValue;
        System.out.println("TRI PAR NOM: " + r);
        Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }

    public List<CompteBancaire> getComptesTriesParSolde(int start, int nb, String order) {
        String orderValue = "";
        if (order.equals("ASCENDING")) {
            orderValue = "ASC";
        } else {
            orderValue = "DESC";
        }
        String r = "select c from CompteBancaire c order by c.solde "
                + orderValue;
        System.out.println("TRI PAR NOM: " + r);
        Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }
    
    public List<CompteBancaireBis> getComptesFiltreparNom(Map<String,Object> filters, int first, int pageSize){
        List<CompteBancaireBis> res = null;
        System.out.println("filtres"+filters);
        for(Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue().toString();
            String r ="";
            String c = "";
            if(key.equals("nom")){
               r = "SELECT c FROM CompteBancaire c WHERE c.nom LIKE '%"+value+"%'";
               c = "SELECT COUNT(c) FROM CompteBancaire c WHERE c.nom LIKE '%"+value+"%'";
            }
            /*if(key.equals("id")){
               r = "SELECT c FROM CompteBancaire c WHERE c.id = "+value;
            }
            if(key.equals("solde")){
               r = "SELECT c FROM CompteBancaire c WHERE c.solde = "+value;
            }*/
            Query q = this.em.createQuery(r);
            Query count = this.em.createQuery(c);
            System.out.println("count : " + count.getFirstResult());
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            return q.getResultList();
        }
         
      
        return res;
    }
}
