/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author BENATHMANE
 */
@Entity
public class CompteEpargne extends CompteBancaire implements Serializable {

    private double tauxEpargne;
    
    public CompteEpargne(){}
    
    public CompteEpargne(String nom, int  solde, double  taux){
        super(nom, solde, "Epargne");
        this.tauxEpargne = taux;
    }
    
    public double getTauxEpargne() {
        return tauxEpargne;
    }

    public void setTauxEpargne(double tauxEpargne) {
        this.tauxEpargne = tauxEpargne;
    }
    
    public void appliquerTaux(){
        solde = (int) (solde * (1 + tauxEpargne));
    }
    
    @Override
    public String toString() {
        return "tp2.CompteEpargne[ id=" + getId() + " ]";
    }
    
}
