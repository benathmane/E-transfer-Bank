/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import entities.OperationBancaire;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BENATHMANE
 */
@Stateless
@LocalBean
public class GestionnaireDuClient {

    @PersistenceContext
    private EntityManager em;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @EJB
    private GestionnaireDuClient gestionnaireDeClient;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Client> getAllClient() {
        Query query = em.createQuery("SELECT c FROM Client c");
        return query.getResultList();
    }

    public void creerClient(String nom, String prenom, Date date, String adresse, String telephone, String mail) {
        Client client = new Client(nom, prenom, date, adresse, telephone, mail);

        /*CompteBancaire compte = new CompteBancaire(nom, solde) {
        };*/

        //OperationBancaire operation = new OperationBancaire("Création du compte", solde);
        //compte.getOperations().add(operation);

        //compte.setClient(client);
        //client.getListeComptesBancaire().add(compte);

        em.persist(client);
        //em.persist(compte);
    }

    public void addClient(Client client) {
        em.persist(client);
    }

    public Client findClientByID(Long id) {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.id =: id");
        query.setParameter("id", id);
        return (Client) query.getSingleResult();
    }

    public Client findClientByName(String nom) {
        Query query = em.createQuery("SELECT c FROM client c WHERE c.nom =:nom");
        query.setParameter("nom", nom);
        try {
            return (Client) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException en) {
            List<Client> clients = (List<Client>) query.getResultList();

            return clients.get(0);
        }
    }

}
