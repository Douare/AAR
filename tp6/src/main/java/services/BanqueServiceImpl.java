package services;

import dao.ClientDao;
import dao.CompteDao;
import dao.LivretDao;
import modele.Client;
import modele.Compte;
import modele.Livret;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class BanqueServiceImpl {
    private ClientDao clientDao;
    private CompteDao compteDao;
    private LivretDao livretDao;

    public BanqueServiceImpl(ClientDao clientDao, CompteDao compteDao, LivretDao livretDao) {
        this.clientDao = clientDao;
        this.compteDao = compteDao;
        this.livretDao = livretDao;
    }

    public void virement(long idSource, long idDestination, double montant){
        Compte source = compteDao.find(idSource);
        Compte dest = compteDao.find(idDestination);

        source.setSolde(source.getSolde()-montant);
        dest.setSolde(dest.getSolde()+montant);
    }

    Collection<Client> getAllClients(){
        return ClientDao.findAll();
    }

    Client getClient(long id){
        return ClientDao.find(id);

    }

    Collection<Livret> getAllLivrets(){
        return LivretDao.findAll();
    }

    Collection<Compte> getAllComptes(){
        return CompteDao.findAll();
    }

    Collection<Compte> getComptesOfClient(long idClient){
        return CompteDao.find(idClient);
    }

    @Transactional
    public void saveClients(Client... clients) {
        for(Client c : clients) {
            clientDao.create(c);
        }
    }

    void deleteClient(long idClient){
        Client client;
    }

}
