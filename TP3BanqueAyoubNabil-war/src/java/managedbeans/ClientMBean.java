/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.GestionnaireDuClient;

/**
 *
 * @author BENATHMANE
 */
@Named(value = "clientMBean")
@SessionScoped
public class ClientMBean implements Serializable {
    
    @EJB
    private GestionnaireDuClient clientManager;

    private String nom;
    private String prenom;
    private Date dateNaiss;
    private String adresse;
    private String telephone;
    private String mail;
    private String message;
    private int solde = 0;

    /**
     * Creates a new instance of ClientMBean
     */
    public ClientMBean() {
    }
    
    public GestionnaireDuClient getClientManager() {
        return clientManager;
    }

    public void setClientManager(GestionnaireDuClient clientManager) {
        this.clientManager = clientManager;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void creerUnClient() {
        clientManager.creerClient(nom, prenom, dateNaiss, adresse, telephone, mail);
        addMessage("Information.", "Opération reussite.");
    }
    
    public String retour() {
        return "Profil.xhtml?faces-redirect=true";
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
}
