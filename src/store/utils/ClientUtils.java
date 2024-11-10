package store.utils;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import store.models.Client;
import store.services.ClientServiceImpl;

public class ClientUtils {
    private static final ClientServiceImpl clientService = new ClientServiceImpl();

    public static String processCreate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCreate a new client: ");
        System.out.println("Enter the client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the client phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter the client address: ");
        String address = scanner.nextLine();

        String result = clientService.createClient(name, Optional.ofNullable(phone), address);

        return "\nClient created successfully!!";
    }

    public static String processSearch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSearch a client: ");
        System.out.println("Enter the client name: ");
        String name = scanner.nextLine();

        ArrayList<Client> clientsResult = clientService.searchClients(Optional.ofNullable(name));
        if (clientsResult.size() <= 0) {
            return "\nNo clients found!!";            
        }
        printClients(clientsResult);
        return "\nSearch completed!!";
    }

    private static void printClients(ArrayList<Client> clients) {
        clients.forEach(System.out::println);
    }

    public static String processUpdate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nUpdate a client: ");
        System.out.println("Enter the client id: (number)");
        String id = scanner.nextLine();
        System.out.println("Enter the client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the client phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter the client address: ");
        String address = scanner.nextLine();


        String result = clientService.updateClientInfo(id, Optional.ofNullable(name), Optional.ofNullable(phone), Optional.ofNullable(address));
        if (result.equals("Invalid")) {
            return "\nInvalid id!!";            
        }
        if (result.equals("Not found")) {
            return "\nClient not found!!";            
        }
        return "\nUpdate completed!!";
    }

    public static String processDelete() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDelete a client: ");
        System.out.println("Enter the client id: (number)");
        String id = scanner.nextLine();

        String result = clientService.deleteClient(id);
        if (result.equals("Invalid")) {
            return "\nInvalid id!!";            
        }
        return "\nDelete completed!!";
    }

    public static String processGet() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nGet a client: ");
        System.out.println("Enter the client id: (number)");
        String id = scanner.nextLine();

        Optional<Object> client = clientService.getClientByCode(id);
        if (client.get().equals("Invalid")) {
            return "\nInvalid id!!";            
        }
        System.out.println(client.get());
        return "Get completed!!";
    }
}
