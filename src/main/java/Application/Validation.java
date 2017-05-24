/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

/**
 *
 * @author Irina
 */
public class Validation {
    MySql mySQL;
    
    public Validation(MySql mysql){
        mySQL = mysql;
    }
    
    public Boolean password (String password){
        //if it is long enought
        //to not contain spaces
        return false;
    }
    
    public Boolean password (String password, String user){
        //if it matches
        return false;
    }
    
    public Boolean email (String email){
        //if it is not already taken and it has @
        return false;
    }
    
    public Boolean username (String username){
        //if it is not already taken
        return false;
    }
    
    public Boolean text (String text){
        //if it doesn't contain injurious words
        return false;
    }
    
}
