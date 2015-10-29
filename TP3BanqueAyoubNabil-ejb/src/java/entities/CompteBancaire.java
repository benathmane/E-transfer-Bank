/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author BENATHMANE
 */
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),})
@Entity
abstract public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    protected String description;
    protected int solde;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    protected List<OperationBancaire> listeOprerations = new ArrayList();

    public CompteBancaire() {

    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void addOperations(String description, int montant) {
        OperationBancaire op = new OperationBancaire(description, montant);
        listeOprerations.add(op);
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        // operation = création
        addOperations("Création du compte", solde);
    }

    public CompteBancaire(String nom, String description, int solde) {
        this.nom = nom;
        this.description = description;
        this.solde = solde;
    }
    

    public void deposer(int montant) {
        solde += montant;
        addOperations("Crédit " + montant, montant);
    }

    public void retirer(int montant) {
        if (solde < montant) {
            throw new EJBException();
        } else {
            solde -= montant;
            addOperations("Débit " + montant, montant);
        }
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public List<OperationBancaire> getListeOprerations() {
        return listeOprerations;
    }

    public void setListeOprerations(List<OperationBancaire> listeOprerations) {
        this.listeOprerations = listeOprerations;
    }

    @Override
    public String toString() {
        return "tp2.CompteBancaire[ id=" + id + " ]";
    }

}
