package fit_zone.presentation;

import fit_zone.data.ClientDAO;
import fit_zone.data.IClientDAO;
import fit_zone.domain.Client;

import java.util.Scanner;

public class FitZoneApp {
    public static void main(String[] args) {
        fitZoneApp();
    }

    private static void fitZoneApp(){
        var exit = false;
        var console = new Scanner(System.in);
        //create new client from class clientDao
        IClientDAO clientDao = new ClientDAO();
        while(!exit){
            try{
                var option = displayMenu(console);
                exit = executeOptions(console, option, clientDao);

            }
            catch(Exception e){
                System.out.println("error while executing options" + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int displayMenu(Scanner console){
        System.out.println("""
                Zona Fit GYM
             1. List clients
             2. Search clients
             3. Add client
             4. Modify client
             5. Eliminate client
             6. Exit
             Choose an option:\s
                """);
        return Integer.parseInt(console.nextLine());
    }
    private static boolean executeOptions(Scanner console, int option, IClientDAO clientDao){
        var exit = false;
        switch(option){
            case 1 -> {
                System.out.println("---List of clients---");
                var clients = clientDao.listClients();
                clients.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Enter the id of the client you want to search: ");
                var idClient = Integer.parseInt(console.nextLine());
                var client = new Client(idClient);
                var found = clientDao.searchClientById(client);
                if(found){
                    System.out.println("Found client: " + client);
                }
                else{
                    System.out.println("Client not found: " + client);
                }
            }
            case 3 ->{
                System.out.println("---Add client ---");
                System.out.println("Name: ");
                var name = console.nextLine();
                System.out.println("Last name: ");
                var lastName = console.nextLine();
                System.out.println("Membership: ");
                var membership = Integer.parseInt(console.nextLine());
                // create the object client
                var client = new Client(name, lastName, membership);
                var added = clientDao.addClient(client);
                if(added){
                    System.out.println("client added: " + client);
                }
                else{
                    System.out.println("Client not added: " + client);
                }
            }
            case 4 ->{
                //modify client
                System.out.println("---Modify client ---");
                System.out.println("Enter Id client: ");
                var idClient = Integer.parseInt(console.nextLine());
                System.out.println("Name: ");
                var name = console.nextLine();
                System.out.println("Last Name: ");
                var lastName = console.nextLine();
                System.out.println("Membership: ");
                var membership = Integer.parseInt(console.nextLine());
                //create the object to modify
                var client = new Client(idClient, name, lastName, membership);
                var modified = clientDao.modifyClient(client);
                if(modified){
                    System.out.println("client modified: " + client);

                }
                else{
                    System.out.println("Client not modified:" + client);
                }
            }
            case 5 ->{
                //delete client
                System.out.println("---Delete client---");
                System.out.println("Id Client: ");
                var idClient = Integer.parseInt(console.nextLine());
                var client = new Client(idClient);
                var deleted = clientDao.eliminateClient(client);
                if(deleted){
                    System.out.println("client deleted: " + client);

                }
                else{
                    System.out.println("Client not deleted: " + client);
                }
            }
            case 6 -> {
                System.out.println("See you soon!");
                exit = true;
            }
            default -> System.out.println("Option not found: " + option);
        }
        return exit;
    }
}
