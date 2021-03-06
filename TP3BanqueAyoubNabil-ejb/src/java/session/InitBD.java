/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

/**
 *
 * @author BENATHMANE
 */
@Singleton
@LocalBean
@Startup    // On créée l'instance de ce Singleton dès
            // le déploiement
public class InitBD {
    @EJB
    private GestionnaireDeCompteBancaire gc;
    @EJB
    private GestionnaireDeUtilisateurs gu;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    public void initBase() {
        System.out.println("#### BD REMPLIE ###");
        gc.creerComptesTest();
        gu.creerUtilisateurTest();
    }
}