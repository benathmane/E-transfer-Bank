/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author BENATHMANE
 */
@Singleton
@LocalBean
@Startup
public class GestionnaireTimer {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /*
    @EJB
    private GestionnaireDeCompteBancaire gcb;
    
    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init() {
        timerService.createTimer(3000,3000, null);
    }

    @Timeout
    public void execute(Timer timer) {
        System.out.println("### timer call ###");
        gcb.appliquertauxwithtimer();
        System.out.println("### timer action done, Time Remaining : " + timer.getTimeRemaining());
    }*/
}
