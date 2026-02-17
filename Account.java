package Account;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Account implements Serializable{
    static ArrayList<String> al = new ArrayList<>();
    static {
        al.add("1.What is your Birth Place?");
        al.add("2.What is your favorite Color?");
        al.add("3.What is your favorite place?");
        al.add("4.What is your favorite person?");
        al.add("5.What is your school name?");
    }
    static int id = 0;
    String UserID;
    String UserName;
    String Password;
    String DOB;
    String email;

    String securityQuestion,securityAnswer;
    LinkedHashMap<String,Account> hm = new LinkedHashMap<>();
    transient Scanner s = new Scanner(System.in);
    public Account()
    {

    }
    public Account(String UserName,String Password,String email,String securityQuestion,String securityAnswer)
    {
        UserID = "UID"+(++id);
        this.UserName = UserName;
        this.Password = Password;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
    public Account(String UserName,String Password,String DOB,String email,String securityQuestion,String securityAnswer)
    {
        UserID = "UID"+(++id);
        this.UserName = UserName;
        this.Password = Password;
        this.DOB = DOB;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
    public String toString()
    {
        return "UserID: "+this.UserID+"\nUserName: "+UserName+"\nDate-Of-Birth: "+DOB+"\nEmail: "+email+"\n";
    }
    public void loadAccounts()
    {
        try(FileInputStream fis = new FileInputStream("Accounts.txt");ObjectInputStream ois = new ObjectInputStream(fis))
        {
            LinkedHashMap<String,Account> lhm = (LinkedHashMap<String,Account>)ois.readObject();
            Set<Entry<String,Account>> entries = lhm.entrySet();
            for(Entry<String,Account> entry : entries)
            {
                hm.put(entry.getKey(),entry.getValue());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void createAccount()
    {
        System.out.println("Creating an account.....");
        System.out.print("Enter Your Name : ");
        String Name = s.nextLine();
        System.out.println("");
        System.out.print("Enter Your Password : ");
        String Password = s.nextLine();
        System.out.println("");
        System.out.print("Confirm Your Password: ");
        String ConPassword = s.nextLine();
        System.out.println("");
        while(!ConPassword.equals(Password))
        {
            System.out.println("Password and Confirm password does'nt Match....");
            System.out.println("Please Re-enter");
            System.out.print("Enter Password: ");
            Password = s.nextLine();
            System.out.println("");
            System.out.print("Confirmation Password: ");
            ConPassword = s.nextLine();
            System.out.println("");
        }
        System.out.print("Enter Your DOB(or press ENTER to SKIP): ");
        String DOB = s.nextLine();
        System.out.println("");
        Account a;
        System.out.print("Enter Email : ");
        String e = s.nextLine();
        System.out.println("");
        System.out.println("Select Security Question: ");
        System.out.println("");
        int questionNo = 0;
        int i = 0;
        for(String s : al)
        {
            System.out.println(s);
        }
        System.out.println("");
        System.out.print("Enter Question Number: ");
        questionNo = s.nextInt();
        System.out.println("");
        String sq = al.get(questionNo-1);
        System.out.print("Enter the Answer: ");
        s.nextLine();
        String sa = s.nextLine();
        System.out.println("");
        if(DOB == null)
            a = new Account(Name, Password,e,sq,sa);
        else
            a = new Account(Name, Password, DOB,e,sq,sa);
        this.hm.put(a.UserID, a);
    }
    public Account deleteAccount()
    {
        System.out.println("Removing Account in Progress....");
        System.out.println("Enter Your UserName:  ");
        String User = s.nextLine();
        String key = null;
        Set<Entry<String,Account>> entries = hm.entrySet();
        for(Entry<String,Account> entry : entries)
        {
            Account a = entry.getValue();
            if(a.UserName.equals(User))
            {
                System.out.println("User Account Found....!");
                key = entry.getKey();
                break;
            }
        }
        int count = 0;
        System.out.println("Enter answer for Security Answer for question....");
        while(count < 3)
        {
            System.out.println(hm.get(key).securityQuestion);
            String sa = s.nextLine();
            if(sa.equals(hm.get(key).securityAnswer))
            {
                System.out.println("Answer Matches...Account removal in progress....");
                break;
            }
            else
            {
                System.out.println("Answer does'nt Matches....Retry.....");
            }
            count++;
        }
        if(count == 3)
        {
            System.out.println("Maximum limit reached...try Again");
            return null;
        }
        else
        {
            Account a = hm.get(key);
            hm.remove(key);
            return a;
        }
    }
    public String viewAccount()
    {
        System.out.println("Enter your User Name: ");
        String user = s.nextLine();
        String key = null;
        Set<Entry<String,Account>> entries = hm.entrySet();
        for(Entry<String,Account> entry : entries)
        {
            Account a = entry.getValue();
            if(a.UserName.equals(user))
            {
                System.out.println("User Account Found....!");
                key = entry.getKey();
                break;
            }
        }
        if(key == null)
        {
            return new String("User Not Found...!");
        }
        return hm.get(key).toString();
    }
    public void viewAllAccounts()
    {
        Set<Entry<String,Account>> users = hm.entrySet();
        for(Entry<String,Account> user : users)
        {
            System.out.println(user.getValue());
        }
    }
    public void saveAccounts()
    {
        try(FileOutputStream fos = new FileOutputStream("Accounts.txt");ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(hm);
            System.out.println("Accounts Saved Successfully...!");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
