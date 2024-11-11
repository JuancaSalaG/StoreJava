package store.services;
import java.util.ArrayList;
import java.util.Optional;

import store.dao.ClientDAOImpl;
import store.interfaces.ClientService;
import store.models.Client;

public class ClientServiceImpl implements ClientService {
    private final ClientDAOImpl clientDAOImpl = ClientDAOImpl.getInstance();
    
    @Override
    public String createClient(String name, Optional<String> phone, String address) {
        String clientPhone = phone.isPresent() ? phone.get() : "";

        int id = getLastClientId() + 1;

        if (clientPhone.isEmpty()) {
            clientDAOImpl.addClient(new Client(name, address, id));
            return String.valueOf(id);
        }
        clientDAOImpl.addClient(new Client(name, clientPhone, clientPhone, id));
        return String.valueOf(id);
    }

    @Override
    public String updateClient(String id, Optional<String> address, Optional<String> orders, Optional<String> name,
            Optional<String> lastOrderDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClient'");
    }

    @Override
    public String deleteClient(String id) {
        int clientId;
        try {
            clientId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "Invalid";
        }
        if (clientId <= 0) {
            return "Invalid";
        }

        int index = clientDAOImpl.getClientIndex(clientId);
        if (index == -1) {
            return "Not found";            
        }
        clientDAOImpl.removeClient(index);
        return id;
    }

    @Override
    public Optional<Object> getClientByCode(String id) {
        int clientId;
        try {
            clientId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return Optional.of("Invalid");
        }

        int index = clientDAOImpl.getClientIndex(clientId);
        if (index == -1) {
            return Optional.of("Not found");            
        }
        return Optional.of(clientDAOImpl.getClient(index));
    }

    @Override
    public ArrayList<Client> searchClients(Optional<String> name) {
        String clientName = name.isPresent() ? name.get() : "";
        if (!name.isPresent()) {
            return clientDAOImpl.getAllClients();
        }
        return clientDAOImpl.searchClients(clientName);
    }

    private int getLastClientId() {
        return clientDAOImpl.getLastClientId();
    }

    @Override
    public String updateClientInfo(String id, Optional<String> name, Optional<String> phone, Optional<String> address) {
        String clientName = name.isPresent() ? name.get() : "";
        String clientPhone = phone.isPresent() ? phone.get() : "";
        String clientAddress = address.isPresent() ? address.get() : "";

        Optional<Object> client = getClientByCode(id);
        if (client.get().equals("Invalid")) {
            return "Invalid";
        }
        if (client.get().equals("Not found")) {
            return "Not found";
        }

        Client clientObj = (Client) client.get();
        if (!clientName.isEmpty()) {
            clientObj.setName(clientName);
        }
        if (!clientPhone.isEmpty()) {
            clientObj.setPhone(clientPhone);            
        }
        if (!clientAddress.isEmpty()) {
            clientObj.setAddress(clientAddress);            
        }
        clientDAOImpl.updateClient(clientObj.getId(), clientObj);
        return id;
    }
}
