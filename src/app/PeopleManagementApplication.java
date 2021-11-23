package app;
/*Main application code (main() method) in this class.  all calls will be called from this class*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import dao.PeopleManagementDAO;
import pojo.Person;

public class PeopleManagementApplication {

    // read input from keyboard
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // call our dao (data access object) methods
    static PeopleManagementDAO dao = new PeopleManagementDAO();

    public static void main(String[] args) throws Exception {
        String option = "";

        do {
            System.out.println("A. List Persons");
            System.out.println("B. Add Person");
            System.out.println("C. Update Person");
            System.out.println("D. Delete Person");
            System.out.println("E. Count number of Persons");
            System.out.println("F. Exit");
            System.out.println("===========================================");
            System.out.println("Enter an option");
            System.out.println("===========================================");
            option = br.readLine();
            System.out.println("\n");

            switch (option.toUpperCase()) {
            case "A":
                listPersons();
                break;

            case "B":
                addPerson();
                break;

            case "C":
                updatePerson();
                break;

            case "D":
                deletePerson();
                break;

            case "E":
                countPersons();
                break;

            case "F":
                System.out.println("******************************THANK YOU********************");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid Option! Please enter again");
                break;
            }
        } while (!option.equals("F"));
    }

    public static void listPersons() {
        System.out.println("-----------------------------------------------");

        // get all the product from the dao getallProducts() method and store
        // them to a Product type productList
        List<Person> personsList = dao.getAllPersons();
        for (Person person : personsList) {
            // display person one by one
            displayPerson(person);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("\n");

    }

    public static void addPerson() throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("Enter First Name:");
        System.out.println("------------------------------------------------");
        String firstName = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Last Name:");
        System.out.println("------------------------------------------------");
        String lastName = br.readLine();
        // after user enters values, store them in a Person variable
        Person product = new Person(0, firstName, lastName);
        int status = dao.addPerson(product);
        if (status == 1) {
            System.out.println("Person added successfully");
        } else {
            System.out.println("ERROR while adding person");
        }
        System.out.println("\n");
    }

    // this method ask the user to enter the person id and enter new first and last
    // name
    public static void updatePerson() throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Person ID:");
        System.out.println("------------------------------------------------");
        int personId = Integer.parseInt(br.readLine());
        System.out.println("------------------------------------------------");
        System.out.println("Enter New First Name:");
        System.out.println("------------------------------------------------");
        String firstName = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Last Name");
        System.out.println("------------------------------------------------");
        String lastName = br.readLine();
        // after user enters values, store them in a Product variable
        Person product = new Person(personId, firstName, lastName);
        int status = dao.updatePerson(product);
        if (status == 1) {
            System.out.println("Person updated successfully");
        } else {
            System.out.println("ERROR while updating person");
        }
        System.out.println("\n");

    }

    public static void deletePerson() throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Person ID:");
        System.out.println("------------------------------------------------");
        int personId = Integer.parseInt(br.readLine());
        int status = dao.deletePerson(personId);
        if (status == 1) {
            System.out.println("Person deleted successfully");
        } else {
            System.out.println("ERROR while deleting person");
        }
        System.out.println("\n");

    }

    // ask number of persons
    public static void countPersons() throws Exception {
        int count = dao.getCount();
        System.out.println("Total number of persons in the database are " + count);
        System.out.println("\n");
    }

    public static void displayPerson(Person person) {
        System.out.println("Person ID: " + person.getId());
        System.out.println("First Name: " + person.getfirstName());
        System.out.println("Last Name: " + person.getLastName());
        System.out.println("\n");
    }

}
