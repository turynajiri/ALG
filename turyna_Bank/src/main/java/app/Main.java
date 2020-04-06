package app;

import bank.Account;
import bank.Client;
import bank.Company;
import bank.Person;
import java.util.ArrayList;

/**
 *
 * @author Jiri.Turyna
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList();
        
        clients.add(new Person("Pekar"));
        clients.add(new Person("Svecova"));
        clients.add(new Company("Skoda"));
        
        clients.get(0).newAccount(1000);
        clients.get(0).newAccount(500);
        clients.get(1).newAccount(1200);
        clients.get(2).newAccount(120);
        
        for (Client client : clients){
            System.out.println(client.salutation() + " ma zustatek " + client.sumOfAll() + " Kc.");
        }

    }

}
