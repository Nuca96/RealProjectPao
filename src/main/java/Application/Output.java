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
    
    public void myProfileMenu(){
        
        System.out.println();
        System.out.println("---------------------------------Welcom !------------------------------------");
        System.out.println();
        
        System.out.println("Press 1 for search for an account");
        System.out.println("Press 2 for change description");
        System.out.println("Press 3 for change password");
        System.out.println("Press 4 for logout");
        System.out.println();
        
        //user Info + how many unseen comments
        //menu: search, description, password, show comments, return to main menu
        
    }
    
    public void friendsProfileMenu(){
        
        //user Info +
        //menu: to see the comments, to add a comment, to return to your profile
        
        System.out.println();
        System.out.println("Press 1 for show your comments");
        System.out.println("Press 2 for write a comment");
        System.out.println("Press 3 for .....");
    
    
    }
    
    public void searchMenu (){
        //select the user you want or 0 for return
    }
    
    public void commentMessage(){
        //"please insert your comment below:"
        System.out.println("Please inser your comment below:");
    }
    
    public void descriptionMessage(){
        //"please insert your description below:"
    }
    
    public void userInfo (String user) throws SQLException{
        //carrefully about the password! it must be secret :D
        ResultSet res=mySQL.allData(user);
        System.out.println();
        System.out.println("----------------------------------------------------Info------------------------------------------------");
        System.out.println("Username: "+res.getString("username"));
        System.out.println("First Name: "+res.getString("firstname"));
        System.out.println("Last Name: "+res.getString("Lasttname"));
        System.out.println("Email: "+res.getString("email"));
        System.out.println("Password: ");
        
        for(int i=0;i<res.getString("password").length();i++){  // I was carrefully :))
            System.out.print("*");
        }
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
    }
    
    public void errorPassword(){
        //incorrect password (the password must have...)
        System.out.println("Incorrect password the password mast have 6 characters , it mast have a number and a capital!");
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
        
        while(rs.next()){
        
            user=rs.getString("user");
            last_name=rs.getString("lastname");
            first_name=rs.getString("firstname");
            email=rs.getString("email");
            
            System.out.println("Username: "+user);
            System.out.println("First Name: "+first_name);
            System.out.println("Last Name: "+last_name);
            System.out.println("Email: "+email);
               
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
