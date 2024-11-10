package store.interfaces;

import java.util.ArrayList;
import java.util.Optional;

import store.models.Client;

public interface ClientService {
    public String createClient(String name, Optional<String> phone, String address);
    public String updateClient(String id,  Optional<String> address, Optional<String> orders, Optional<String> name, Optional<String> lastOrderDate);
    public String updateClientInfo(String id, Optional<String> name, Optional<String> phone, Optional<String> address);
    public String deleteClient(String id);
    public Optional<Object> getClientByCode(String id);
    public ArrayList<Client> searchClients(Optional<String> name);
}
