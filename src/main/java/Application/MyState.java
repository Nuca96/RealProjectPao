/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Irina
 */
public class MyState {
    
    private MySql mySQL;
    private Input input;
    private Output output;
    private Validation valid;
    
    public MyState(Input i, Output o, Validation v, MySql m){
        input=i;
        valid = v;
        mySQL = m;
        output = o;
    }
    
    public void writeComment(String currentUsername, String anotherUsername){
        //currentUsername wants to write a comment to anotherUsername
        //you don't have to call another state or menu method
        
        output.commentMessage();
        
        String comment = input.getText();
        if (valid.text(comment)){
            mySQL.insertComment(comment, currentUsername, anotherUsername);
        }
        else{
            output.errorCensored();
            writeComment(currentUsername, anotherUsername);
        }
    }
    
    
    public String searchUser (String currentUser) throws SQLException{
        //returns a username 
        //you could create and use another method because this one is kind of complicated
        
        output.searchState();
        
        String name;
        name = input.getWord();
        
        ResultSet rs1 = mySQL.searchResults(name);
        output.searchResults(rs1);
        rs1.close();
        ResultSet rs2 = mySQL.searchResults(name);
        
        int choice = input.getInt();
        int iterator = 0;
        
        while (rs2.next()){
            iterator++;
            if (iterator == choice){
                return rs2.getString("username");
            }
        }
        
        return currentUser;
    }
    
    
    public void changeDescription(String currentUser){
        //you mustn't call another menu or state function
        output.descriptionMessage();
        String description;
        description = input.getText();
        if (valid.text(description)){
            mySQL.setDescription(currentUser, description);
        }
        else{
            output.errorCensored();
            changeDescription(currentUser);
        }
    }
    
    public void changePassword(String currentUser) throws SQLException{
        //the same comment
        output.passwordState();
        
        String currentPassword;
        String newPassword;
        
        currentPassword = input.getWord();
        newPassword = input.getWord();
        
        Boolean ok = true;
        
        if (valid.password(newPassword) && valid.password(currentPassword, currentUser)){
            mySQL.changePassword(currentUser, newPassword);
        }
        else{
            output.errorPassword();
            changePassword(currentUser);
        }
    }
    
    
    
    public String login() throws SQLException{
        output.loginState();
        
        String username = input.getWord();
        String password = input.getWord();
        
        if (valid.password(password, username)){
            return username;
        }
        
        return "";
    }
    
    public String createAccount() throws SQLException{
        
        output.createAccountState();
        
        String username = input.getWord();
        String email = input.getWord();
        String firstname = input.getWord();
        String lastname = input.getWord();
        String password = input.getWord();
        
        Boolean ok=true;
        
        if (!valid.username(username)){
            output.errorUsername();
            ok=false;
        }
        if (!valid.email(email)){
            output.errorEmail();
            ok=false;
        }
        if (!valid.password(password)){
            output.errorPassword();
            ok=false;
        }
        
        if (ok){
            mySQL.insertUser(username, firstname, lastname, email, password);
            return username;
        }
        
        return "";
    }
  
    
}
