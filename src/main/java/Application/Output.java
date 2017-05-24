
package Application;

import java.sql.ResultSet;

public class Output {
    
    private MySql mySQL;
    
    public Output(MySql mysql){
        mySQL=mysql;
    }
    
    public void mainMenu(){
        //the first menu
    }
    
    public void myProfileMenu(){
        //user Info + how many unseen comments
        //menu: search, description, password, show comments, return to main menu
        
    }
    
    public void friendsProfileMenu(){
        //user Info +
        //menu: to see the comments, to add a comment, to return to your profile
    }
    
    public void searchMenu (){
        //select the user you want or 0 for return
    }
    
    public void commentMessage(){
        //"please insert your comment below:"
    }
    
    public void descriptionMessage(){
        //"please insert your description below:"
    }
    
    public void userInfo (String user){
        //carrefully about the password! it must be secret :D
    }
    
    public void comments(String user){
        //obv
    }
    
    public void errorUsername(){
        //e.g. invalid username
    }
    
    public void errorEmail(){
        //
    }
    
    public void errorPassword(){
        //incorrect password (the password must have...)
    }
    
    public void errorCensored(){
        // Sout("don't use injurious words, you son af a bitch!")
    }
    
    public void searchResults(ResultSet rs){
        // all the results + menu for specify the right person
        // carrefully if there are no results
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
