/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
        if (password.length() < 6)
            return false;

        boolean validLitera = false;    
        for (int i = 0; i < password.length(); i++)
            if ((password.charAt(i) >= 'A') && (password.charAt(i) <= 'Z'))
                validLitera = true;

        boolean validCifra = false;
        for (int i = 0; i < password.length(); i++)
            if ((password.charAt(i) >= '0') && (password.charAt(i) <= '9'))
                validCifra = true;

        if ((validLitera == false) || (validCifra == false))
            return false;

        return true;
    }
    
    public Boolean password (String password, String user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
        //if it matches
        
        
        
        String pass = mySQL.getPassword(user);
        //System.out.println(mySQL.getPassword(user));
        return pass.equals(password);
        
    }
    
    public Boolean email (String email){
        //if it is not already taken and it has @
        
        boolean valid=false;    //I suspect that email don't have @ and search "@" in String "email"
        
        for(int i=0;i<email.length();i++){
            if(email.charAt(i)=='@')
                valid=true;
        }
        
        return valid;
    }
    
    public Boolean username (String username) throws SQLException{
        //if it is not already taken
        
        boolean ok=true;
        ResultSet res = mySQL.allUserNames();
        
        while(res.next()){
            
            String currentUser;
            currentUser=res.getString("username");
            if(currentUser.equals(username))
                ok=false;
            
        }
        
        return ok;
    }
    
    public Boolean text (String text){
        //if it doesn't contain injurious words
        return true;
    }
    
}
