/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import session.GestionnaireDeCompteBancaire;
import tp2.CompteBancaire;

/**
 *
 * @author BENATHMANE
 */
@ManagedBean
@ViewScoped
public class CompteBancaireMBean implements Serializable {

    @EJB
    private GestionnaireDeCompteBancaire gc;

    /**
     * Creates a new instance of CompteBancaireMBean
     */
    public CompteBancaireMBean() {

    }

    private int idCompteACrediter;

    /**
     * Get the value of idCompteACrediter
     *
     * @return the value of idCompteACrediter
     */
    public int getIdCompteACrediter() {
        return idCompteACrediter;
    }

    /**
     * Set the value of idCompteACrediter
     *
     * @param idCompteACrediter new value of idCompteACrediter
     */
    public void setIdCompteACrediter(int idCompteACrediter) {
        this.idCompteACrediter = idCompteACrediter;
    }

    public void creerCompteBancaire() {
        System.out.println("### Compte creer ###");
        gc.creerComptesTest();
        refreshListeDesComptes();
    }
    
    private List<CompteBancaire> listeDesComptes;
    
    private void refreshListeDesComptes(){
        listeDesComptes = gc.findAll();
    }
    
    public List<CompteBancaire> getComptesBancaires() {
        if (listeDesComptes == null){
            refreshListeDesComptes();
        }
        return listeDesComptes;
    }

    public void crediterUnCompte() {
        gc.crediterCompte(idCompteACrediter, montantACrediter);
        refreshListeDesComptes();
    }
    private int montantADebiter;

    /**
     * Get the value of montantADebiter
     *
     * @return the value of montantADebiter
     */
    public int getMontantADebiter() {
        return montantADebiter;
    }

    /**
     * Set the value of montantADebiter
     *
     * @param montantADebiter new value of montantADebiter
     */
    public void setMontantADebiter(int montantADebiter) {
        this.montantADebiter = montantADebiter;
    }

    private int montantACrediter;

    /**
     * Get the value of montantACrediter
     *
     * @return the value of montantACrediter
     */
    public int getMontantACrediter() {
        return montantACrediter;
    }

    /**
     * Set the value of montantACrediter
     *
     * @param montantACrediter new value of montantACrediter
     */
    public void setMontantACrediter(int montantACrediter) {
        this.montantACrediter = montantACrediter;
    }

    
    public void debiterUnCompte() {
        gc.debiterCompte(idCompteADebiter, montantADebiter);
        refreshListeDesComptes();
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
    
    private int montant;

    /**
     * Get the value of montant
     *
     * @return the value of montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Set the value of montant
     *
     * @param montant new value of montant
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public void transferer() {
        // MAUVAIS : DEUX TRANSACTIONS ICI !!!
        //gc.debiterUnCompte(id1, montantTransfert);
        //gc.crediterUnCompte(id2, montantTransfert);
        try {
            gc.transferer(id1, id2, montant);
            refreshListeDesComptes();
        } catch(Exception e) {
            message = "Transfert impossible, pas assez d'argent";
            System.out.println("### PAS ASSEZ d'argent");
        }
    }
    private int id1;

    /**
     * Get the value of id1
     *
     * @return the value of id1
     */
    public int getId1() {
        return id1;
    }

    /**
     * Set the value of id1
     *
     * @param id1 new value of id1
     */
    public void setId1(int id1) {
        this.id1 = id1;
    }

    private int id2;

    /**
     * Get the value of id2
     *
     * @return the value of id2
     */
    public int getId2() {
        return id2;
    }

    /**
     * Set the value of id2
     *
     * @param id2 new value of id2
     */
    public void setId2(int id2) {
        this.id2 = id2;
    }

    private String message;

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }


}
