package  Account;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Account a = new Account();
        a.loadAccounts();
        System.out.println("1.Create Account\n2.Remove Account\n3.View Account\n4.View All Accounts\n5.Save Accounts\n6.Exit\n");
        System.out.println("Enter your Choice(int): ");
        int choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        do
        {
            switch(choice)
            {
                case 1 :a.createAccount();
                        break;
                case 2 : a.deleteAccount();
                        break;
                case 3 :System.out.println("Details "+a.viewAccount());
                        break;
                case 4 : a.viewAllAccounts();
                        break;
                case 5 : a.saveAccounts();
                        break;
                case 6 : a.saveAccounts();
                        break;
            }
        System.out.println("Any Other Queries from Above: ");
        System.out.println("Enter Choice: ");
        choice  = s.nextInt();
        }while(choice != 6);
}
}
