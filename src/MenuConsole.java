import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

import store.models.Store;
import store.utils.*;

public class MenuConsole {
    private static final Scanner scanner = new Scanner(System.in);
    private Store store;
    private HashMap <Integer, Runnable> menuOptions = new HashMap<>();

    public MenuConsole() {
        menuOptions.put(1, this::inventoryMenu);
        /* menuOptions.put(2, this::goToSales);
        menuOptions.put(3, this::goToCustomers); */
        menuOptions.put(4, () -> {
            System.out.println("Thanks for using the system, see you soon!!");
            System.exit(0);
        });
    }
    
    public void initProcess() {
        System.out.println("Welcome to online store management system!!");
        System.out.println("Please enter the store information:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        store = new Store(name, phone, city);

        System.out.println("Store created successfully!!\n");
        System.out.println(store);
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n===========================");
            System.out.println(store.getName());
            System.out.println("===========================");
            System.out.println("Choice an option: (Number)");
            System.out.println("1. Go to inventory 📦");
            System.out.println("2. Go to sales and orders 💰");
            System.out.println("3. Go to customers 👥");
            System.out.println("4. Exit 🚪\n");
            try {
                int option = scanner.nextInt();
                if (option > 4 || option < 1) {
                    throw new Exception();                    
                }
                menuOptions.get(option).run();
            } catch (Exception e) {
                System.out.println("Invalid option, please try again.");
                scanner.nextLine();
                showMenu();
            }
        }
    }

    private void inventoryMenu() {
        HashMap <String, Supplier<String>> inventoryOptions = new HashMap<>();
        inventoryOptions.put("create", ProductUtils::processCreate);
        inventoryOptions.put("update", ProductUtils::processUpdate);
        inventoryOptions.put("delete", ProductUtils::processDelete);
        inventoryOptions.put("get", ProductUtils::processGet);
        inventoryOptions.put("search", ProductUtils::processSearch);

        System.out.println("\n===========================");
        System.out.println("Inventory menu 📦");
        System.out.println("===========================");
        System.out.println("Type the action you want to do.");
        System.out.println("A. Create a product: (Opt → Create)");
        System.out.println("B. Update a product: (Opt → Update)");
        System.out.println("C. Delete a product: (Opt → Delete)");
        System.out.println("D. Get a product: (Opt → Get)");
        System.out.println("E. Search products: (Opt → Search)");
        System.out.println("F. Back to main menu: (Opt → Back)\n");
        try {
            String option = scanner.next().toLowerCase();
            if (!inventoryOptions.containsKey(option) && !option.equals("back")) {
                throw new Exception();
            }
            if (option.equals("back")) {
                showMenu();                
            }
            String result = inventoryOptions.get(option).get();
            System.out.println(result);
            inventoryMenu();            
        } catch (Exception e) {
            System.out.println("Invalid inventory option, please try again.");
            scanner.nextLine();
            inventoryMenu();
        }
    }
}
