package store.dao;

import java.util.ArrayList;
import java.util.Optional;

import store.interfaces.ClientDAO;
import store.models.*;

public class ClientDAOImpl implements ClientDAO {
    ArrayList<Client> clients = new ArrayList<Client>();

    public ClientDAOImpl() {
        clients.add(new Client("John", "123456789", "New York", 1));
        clients.add(new Client("Valentina", "123456589", "Texas", 2));
        clients.add(new Client("Andrés Raul", "123456766", "California", 3));
        clients.add(new Client("María Fernanda", "335456789", "Montana", 4));
    }

    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public void removeClient(int id) {
        clients.remove(id);
    }

    @Override
    public void updateClient(int id, Client client) {
        clients.set(id, client);
    }

    @Override
    public Client getClient(int id) {
        return clients.get(id);
    }

    @Override
    public ArrayList<Client> searchClients(String name) {
        ArrayList<Client> clients = new ArrayList<Client>();
        for (Client client : this.clients) {
            if (client.getName().toLowerCase().contains(name.toLowerCase())) {
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public ArrayList<Client> getAllClients() {
        return clients;
    }

    @Override
    public int getLastClientId() {
        return clients.get(clients.size() - 1).getId();
    }

    public int getClientIndex(int id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
