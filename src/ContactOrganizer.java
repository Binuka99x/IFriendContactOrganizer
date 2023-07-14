import java.util.*;

public class ContactOrganizer {
    public static void homePage() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("                 iFriend Contacts Organizer                 ");
        System.out.println("------------------------------------------------------------\n");
        System.out.println("[01] Add Contacts");
        System.out.println("[02] Update Contacts");
        System.out.println("[03] Delete Contacts");
        System.out.println("[04] Search Contacts");
        System.out.println("[05] List Contacts");
        System.out.println("[06] Exit\n");
        System.out.print("Enter an option to continue : ");
        int opt = input.nextInt();
        switch (opt) {
            case 1:
                addContacts();
                break;
            case 2:
                updateContacts();
                break;
            case 3:
                deleteContacts();
                break;
            case 4:
                searchContacts();
                break;
            case 5:
                listContacts();
                break;
            case 6:
                System.exit(0);
        }
    }
    public static void addContacts(){

    }
    public static void updateContacts(){

    }
    public static void deleteContacts(){

    }
    public static void searchContacts(){

    }
    public static void listContacts(){

    }

    public static void main(String[] args) {
        homePage();
    }
}
