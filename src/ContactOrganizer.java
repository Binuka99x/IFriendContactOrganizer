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
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getSalary() {
        return salary;
    }

    public String getDob() {
        return dob;
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
                clearConsole();
                addContacts();
                break;
            case 2:
                clearConsole();
                updateContacts();
                break;
            case 3:
                clearConsole();
                deleteContacts();
                break;
            case 4:
                clearConsole();
                searchContacts();
                break;
            case 5:
                clearConsole();
                listContacts();
                break;
            case 6:
                System.exit(0);
        }
    }

    public static void addContacts() {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("|                      Add Contact                         |");
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
                clearConsole();
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
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("------------------------------------------------------------");
            System.out.println("|                      Update Contact                      |");
            System.out.println("------------------------------------------------------------\n");
            System.out.print("Search Contact by Name or Phone Number : ");
            String nameOrPhone = input.next();
            int index = search(nameOrPhone);
            if (index == -1) {
                System.out.println("No Contact found for \"" + nameOrPhone + "\"");
                continue;
            }
            System.out.println("Contact ID   : " + contactsArray[index].getId());
            System.out.println("Name         : " + contactsArray[index].getName());
            System.out.println("Phone Number : " + contactsArray[index].getPhoneNumber());
            System.out.println("Company Name : " + contactsArray[index].getCompanyName());
            System.out.println("Salary       : " + contactsArray[index].getSalary());
            System.out.println("Birthday     : " + contactsArray[index].getDob());
            System.out.println("\nWhat do you want to update....\n");
            System.out.println("[01] Name");
            System.out.println("[02] Phone Number");
            System.out.println("[03] Company Name");
            System.out.println("[04] Salary");
            System.out.print("Enter an option to continue : ");
            int opt = input.nextInt();
            switch (opt) {
                case 1:
                    updateName(index);
                    break;
                case 2:
                    updatePhoneNumber(index);
                    break;
                case 3:
                    updateCompanyName(index);
                    break;
                case 4:
                    updateSalary(index);
                    break;
            }
        } while (true);
    }
    public static void updatePhoneNumber(int index){
        Scanner input = new Scanner(System.in);
        System.out.print("\033[7A");
        System.out.print("\033[0J");
        System.out.println("Update Phone Number");
        System.out.println("====================");
        String phoneNumber;
        do {
            System.out.print("Input New Phone Number : ");
            phoneNumber = input.next();
            if (!isValidPhoneNumber(phoneNumber)){
                System.out.println("Invalid Phone Number....Try again..");
                continue;
            }
            break;
        }while (true);
        contactsArray[index].setPhoneNumber(phoneNumber);
        System.out.println("Contact update successfully...");
        System.out.print("Do you want to update a another Contact(Y/N) : ");
        String opt = input.next();
        if (opt.equalsIgnoreCase("Y")) {
            clearConsole();
            updateContacts();
        } else if (opt.equalsIgnoreCase("N")) {
            homePage();
        }
    }
    public static void updateSalary(int index){
        Scanner input = new Scanner(System.in);
        System.out.print("\033[7A");
        System.out.print("\033[0J");
        System.out.println("Update Salary");
        System.out.println("==============");
        double salary;
        do {
            System.out.print("Input New Salary : ");
            salary = input.nextDouble();
            if (salary<0){
                System.out.println("Invalid Salary....Try again..");
                continue;
            }
            break;
        }while (true);
        contactsArray[index].setSalary(salary);
        System.out.println("Contact update successfully...");
        System.out.print("Do you want to update a another Contact(Y/N) : ");
        String opt = input.next();
        if (opt.equalsIgnoreCase("Y")) {
            clearConsole();
            updateContacts();
        } else if (opt.equalsIgnoreCase("N")) {
            homePage();
        }
    }
    public static void updateCompanyName(int index){
        Scanner input = new Scanner(System.in);
        System.out.print("\033[7A");
        System.out.print("\033[0J");
        System.out.println("Update Company Name");
        System.out.println("====================");
        System.out.print("Input New Company Name : ");
        String companyName = input.next();
        contactsArray[index].setCompanyName(companyName);
        System.out.println("Contact update successfully...");
        System.out.print("Do you want to update a another Contact(Y/N) : ");
        String opt = input.next();
        if (opt.equalsIgnoreCase("Y")) {
            clearConsole();
            updateContacts();
        } else if (opt.equalsIgnoreCase("N")) {
            homePage();
        }
    }
    public static void updateName(int index){
        Scanner input = new Scanner(System.in);
        System.out.print("\033[7A");
        System.out.print("\033[0J");
        System.out.println("Update Name");
        System.out.println("============");
        System.out.print("Input New Name : ");
        String name = input.next();
        contactsArray[index].setName(name);
        System.out.println("Contact update successfully...");
        System.out.print("Do you want to update a another Contact(Y/N) : ");
        String opt = input.next();
        if (opt.equalsIgnoreCase("Y")) {
            clearConsole();
            updateContacts();
        } else if (opt.equalsIgnoreCase("N")) {
            homePage();
        }
    }

    public static int search(String nameOrPhone) {
        for (int i = 0; i < contactsArray.length; i++) {
            if (contactsArray[i].getPhoneNumber().equalsIgnoreCase(nameOrPhone) || contactsArray[i].getName().equalsIgnoreCase(nameOrPhone)) {
                return i;
            }
        }
        return -1;
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
