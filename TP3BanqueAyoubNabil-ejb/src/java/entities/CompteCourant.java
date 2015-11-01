/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author BENATHMANE
 */
@Entity
public class CompteCourant extends CompteBancaire implements Serializable {
    public  CompteCourant(){};
    
    public CompteCourant(String nom, int solde){
        super(nom, solde, "Courant");
    }

    @Override
    public String toString() {
        return "tp2.CompteCourant[ id=" + getId() + " ]";
    }
    
}
