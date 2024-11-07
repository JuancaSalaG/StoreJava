import java.util.Scanner;
import store.models.Store;

public class MenuConsole {
    private static final Scanner scanner = new Scanner(System.in);
    private Store store;
    
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
            
        }
    }
}
