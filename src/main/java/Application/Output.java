/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Output {
    
    private MySql mySQL;
    
    public Output(MySql mysql){
        mySQL=mysql;
    }
    
    public void mainMenu(){
        System.out.println("Press 1 for login");
        System.out.println();
        System.out.println("Press 2 for create new account");
        System.out.println();
        System.out.println("Press 3 for exit the aplication");
    }
    
    public void myProfileMenu(String user) throws SQLException{
        
        System.out.println();
        System.out.println("---------------------------------Welcome !------------------------------------");
        System.out.println();
        userInfo(user);
        
        
        System.out.println("You have "+mySQL.unseenComments(user)+" unseen comments!");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        
        System.out.println("Press 1 for search for an account");
        System.out.println("Press 2 for change description");
        System.out.println("Press 3 for change password");
        System.out.println("Press 4 for comments");
        System.out.println("Press 5 for logout");
        System.out.println();
        
        //user Info + how many unseen comments
        //menu: search, description, password, show comments, return to main menu
        
    }
    
    public void friendsProfileMenu(String user) throws SQLException{
        
        //user Info +
        //menu: to see the comments, to add a comment, to return to your profile
        userInfo(user);
        System.out.println();
        System.out.println("Press 1 for show your comments");
        System.out.println("Press 2 for write a comment");
        System.out.println("Press 3 for go back to your profile");
    
    
    }
    
    public void searchState(){
        
        System.out.println("Please insert a part of a name, username or email:");
    }
    
    public void searchMenu (){
        //select the user you want or 0 for return
        System.out.println("Select the user you want or 0 for return to your profile:");
    }
    
    public void commentMessage(){
        //"please insert your comment below:"
        System.out.println("Please insert your comment below:");
    }
    
    public void descriptionMessage(){
        //"please insert your description below:"
        System.out.println("Please insert your description below:");
    }
    
    public void userInfo (String user) throws SQLException{
        //carrefully about the password! it must be secret :D
        ResultSet res=mySQL.allData(user);
        System.out.println();
        System.out.println("----------------------------------------------------Info------------------------------------------------");
        res.next();
        System.out.println("Username: "+res.getString("username"));
        System.out.println("First Name: "+res.getString("firstname"));
        System.out.println("Last Name: "+res.getString("lastname"));
        System.out.println("Email: "+res.getString("email"));
        System.out.println("Description: "+res.getString("description"));
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
    
    public void comments(String user) throws SQLException{
        
        ResultSet res=mySQL.allComments(user);
        
        while(res.next()){
            String sender;
            String comm;
            
            sender=res.getString("sender");
            comm=res.getString("comment");
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println(sender+": "+comm);
            System.out.println("--------------------------------------------------------------------------------------------------------");
        }
        
    }
    
    public void errorUsername(){
        //e.g. invalid username
        System.out.println("Username error please try again!");
        System.out.println();
    }
    
    public void errorEmail(){
        System.out.println("Incorect email please try again!");
        System.out.println();
    }
    
    public void errorPassword(){
        //incorrect password (the password must have...)
        System.out.println("Incorrect password the password mast have 6 characters , it must have a number and a capital!");
        System.out.println();
    }
    
    
            
    public void errorCensored(){
        // Sout("don't use injurious words, you son af a bitch!")
    }
    
    public void searchResults(ResultSet rs) throws SQLException{
        // all the results + menu for specify the right person
        // carrefully if there are no results
        
        String user;
        String last_name;
        String first_name;
        String email;
        
        int iterator=0;
        
        while(rs.next()){
        
            user=rs.getString("username");
            last_name=rs.getString("lastname");
            first_name=rs.getString("firstname");
            email=rs.getString("email");
            
            iterator++;
            
            System.out.println(iterator);
            System.out.println("Username: "+user);
            System.out.println("First Name: "+first_name);
            System.out.println("Last Name: "+last_name);
            System.out.println("Email: "+email);
            System.out.println();
               
        }
    }
    
    
    
    /* I don't know what to do when we have those kind of situations:
    
    output("first name: ");
    input(firstName);
    output("last name: ");
    input...;
    output("Username: ");
    input...;
    output("password: ");
    input...;
    output("email: ");
    input...;
    
    ---> in create account menu, change password menu, login menu
    */
    
    //I know this is not a good idea, but:
    
    public void createAccountState(){
          System.out.println("Insert, in order: username, email, first name, last name, password");
    }
    
    public void passwordState(){
        System.out.println("Insert your current password, then your new password");
    }
    
    public void loginState(){
        System.out.println("Insert your username then your password");
    }
    
}
