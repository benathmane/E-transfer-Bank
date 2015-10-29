/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import session.GestionnaireDeCompteBancaire;
import entities.CompteBancaire;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author BENATHMANE
 */
@ManagedBean
@ViewScoped
public class CompteBancaireMBean implements Serializable {

    @EJB
    private GestionnaireDeCompteBancaire gc;
    private List<CompteBancaire> listeDesComptes;
    private int idCompteACrediter;
    private int montantACrediter;
    private int idCompteADebiter;
    private int montantADebiter;
    private int id1;
    private int id2;
    private int montant;
    private String message;
    private LazyDataModel LazyModelComptesBacaires;
    
    private List<CompteBancaire> CheckDesComptes;

    public List<CompteBancaire> getCheckDesComptes() {
        return CheckDesComptes;
    }

    public void setCheckDesComptes(List<CompteBancaire> CheckDesComptes) {
        this.CheckDesComptes = CheckDesComptes;
    }

    
    /**
     * Creates a new instance of CompteBancaireMBean
     */
    public CompteBancaireMBean() {
        // On creer une instance du LazyDataModel
        LazyModelComptesBacaires = new LazyDataModel<CompteBancaire>() {

            @Override
            public List<CompteBancaire> load(int start, int nb,
                    String nomChamp, SortOrder so,
                    Map map) {
                // A ecrire
                System.out.println("### load : start =" + start + " nb = " + nb + "nom colonne = " + nomChamp);
                if (nomChamp != null) {
                    if (nomChamp.equals("nom")) {
                        // Il faut trier
                        System.out.println("Tri: champ= "
                                + nomChamp + " ordre: " + so.name());
                        return gc.getComptesTriesParNom(start, nb, so.name());
                    } else if (nomChamp.equals("solde")){
                        // Il faut trier
                        System.out.println("Tri: champ= "
                                + nomChamp + " ordre: " + so.name());
                        return gc.getComptesTriesParSolde(start, nb, so.name());
                    }
                } else {
                    // Juste la pagination, pas de tri, de filtre
                    return gc.getComptes(start, nb);
                }
                return null;
            }

            @Override
            public int getRowCount() {
                return (int) gc.getNombreDeComptes();
            }
        };
    }

    public LazyDataModel getLazyModelComptesBacaires() {
        return LazyModelComptesBacaires;
    }

    public void setLazyModelComptesBacaires(LazyDataModel LazyModelComptesBacaires) {
        this.LazyModelComptesBacaires = LazyModelComptesBacaires;
    }

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

    private void refreshListeDesComptes() {
        listeDesComptes = gc.findAll();
    }

    public List<CompteBancaire> getComptesBancaires() {
        if (listeDesComptes == null) {
            refreshListeDesComptes();
        }
        return listeDesComptes;
    }

    public LazyDataModel getlazyComptesBancaires() {
        return LazyModelComptesBacaires;
    }

    public void crediterUnCompte() {
        if (montantACrediter == 0){
            addMessage("Erreur.", "Le montant est égal à zéro.");
        } else {
            gc.crediterCompte(idCompteACrediter, montantACrediter);
            addMessage("Information.", "Opération reussite.");
            refreshListeDesComptes();
        }
    }

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
        if (montantADebiter == 0){
            addMessage("Erreur.", "Le montant est égal à zéro.");
        } else {
            gc.debiterCompte(idCompteADebiter, montantADebiter);
            addMessage("Information.", "Opération reussite.");
            refreshListeDesComptes();
        }
    }

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
            if (id1 == id2){
                addMessage("Erreur.", "Compte à déditer = Compte à créditer.");
            } else if (montant == 0){
                addMessage("Erreur.", "Le montant est égal à zéro.");
            } else {
                gc.transferer(id1, id2, montant);
                addMessage("Information.", "Opération reussite.");
                refreshListeDesComptes();
            }
        } catch (Exception e) {
            message = "Transfert impossible, pas assez d'argent";
            System.out.println("### PAS ASSEZ d'argent");
        }
    }

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

    public void supprimerCompte(CompteBancaire c) {
        gc.supprimerCompte(c);
        addMessage("Information.", "Suppression effectuée.");
        refreshListeDesComptes();
    }

    public void addMessage(String summary, String detail) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void supprimer() {
        if (CheckDesComptes.isEmpty()){
            addMessage("Erreur.", "Merci de sélectionner un/plusieurs comptes.");
        }else{
            for(CompteBancaire c:CheckDesComptes)
            {
                System.out.println("### Suppression du compte " + c.getId());
                gc.supprimerCompte(c);
            }
            addMessage("Information.", "Suppression effectuée.");
            refreshListeDesComptes();
        }
        
    }

}
