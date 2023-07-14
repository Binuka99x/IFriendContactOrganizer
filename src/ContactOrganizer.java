import java.time.LocalDate;
import java.util.*;

class Contacts {
    private String id;
    private String name;
    private String phoneNumber;
    private String companyName;
    private String dob;
    private double salary;

    Contacts(String id, String name, String phoneNumber, String companyName, double salary, String dob) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.salary = salary;
        this.dob = dob;
    }
}

public class ContactOrganizer {
    public static Contacts[] contactsArray = new Contacts[0];

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

    public static void addContacts() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("                       Add Contact                          ");
        System.out.println("------------------------------------------------------------\n");
        String id;
        String name;
        String phoneNumber;
        String companyName;
        double salary;
        String dob;
        boolean validPhoneNumber;
        boolean validDob;
        do {
            id = generateContactID();
            System.out.println(id + "\n=======\n");
            System.out.print("Name              : ");
            name = input.next();
            do {
                System.out.print("Phone Number      : ");
                phoneNumber = input.next();
                validPhoneNumber = isValidPhoneNumber(phoneNumber);
                if (!validPhoneNumber) {
                    System.out.print("\tInvalid Phone Number...\nDo you want to add phone number again(Y/N:)");
                    String opt = input.next();
                    if (opt.equalsIgnoreCase("Y")) {
                        continue;
                    } else if (opt.equalsIgnoreCase("N")) {
                        homePage();
                    }
                }
            } while (!validPhoneNumber);
            System.out.print("Company Name      : ");
            companyName = input.next();
            do {
                System.out.print("Salary            : ");
                salary = input.nextDouble();
                if (salary < 0) {
                    System.out.println("Wrong input...Please try again..");
                    continue;
                } else {
                    break;
                }
            } while (true);
            do {
                System.out.print("B'Day(YYYY-MM-DD)	: ");
                dob = input.next();
                validDob = isValidDob(dob);
                if (!validDob) {
                    System.out.print("\tInvalid Birthday...\nDo you want to add Birthday again(Y/N:)");
                    String opt = input.next();
                    if (opt.equalsIgnoreCase("Y")) {
                        continue;
                    } else if (opt.equalsIgnoreCase("N")) {
                        homePage();
                    }
                }
            } while (!validDob);
            Contacts contacts = new Contacts(id, name, phoneNumber, companyName, salary, dob);
            extendArray();
            contactsArray[contactsArray.length - 1] = contacts;
            System.out.println("Contact has been added successfully...");
            System.out.print("Do you want to add another Contact (Y/N) : ");
            String opt = input.next();
            if (opt.equalsIgnoreCase("Y")) {
                clearConsole();
                addContacts();
            } else if (opt.equalsIgnoreCase("N")) {
                homePage();
                break;
            }
        } while (true);
    }

    public static void extendArray() {
        Contacts[] tempContactsArray = new Contacts[contactsArray.length + 1];
        for (int i = 0; i < contactsArray.length; i++) {
            tempContactsArray[i] = contactsArray[i];
        }
        contactsArray = tempContactsArray;
    }

    public static boolean isValidDob(String dob) {
        String y = dob.substring(0, 4);
        int year = Integer.parseInt(y);
        String m = dob.substring(5, 7);
        int month = Integer.parseInt(m);
        String d = dob.substring(8);
        int day = Integer.parseInt(d);

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        if (year < currentYear) {
            return true;
        }
        if (year == currentYear) {
            if (month < currentMonth) {
                return true;
            } else if (month == currentMonth) {
                if (day <= currentDay) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 10) {
            return true;
        }
        return false;
    }

    public static String generateContactID() {
        return String.format("C%04d", (contactsArray.length + 1));
    }

    public static void updateContacts() {

    }

    public static void deleteContacts() {

    }

    public static void searchContacts() {

    }

    public static void listContacts() {

    }

    public static void main(String[] args) {
        homePage();
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
// Handle any exceptions.
        }
    }
}
