package store.interfaces;

import java.util.ArrayList;
import java.util.Optional;
import store.models.Client;

public interface ClientDAO {
    public void addClient(Client client);
    public void removeClient(int id);
    public void updateClient(int id, Client client);
    public Client getClient(int id);
    public ArrayList<Client> searchClients(String name);
    public ArrayList<Client> getAllClients();
    public int getLastClientId();
    public void addOrderToClient(int id);
}
