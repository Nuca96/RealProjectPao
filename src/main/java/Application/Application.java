package Application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
public class Application {
    
    private MySql mySQL;
    private Input input;
    private Output output;
    private Validation valid;
    private MyState state;
    
    
    public Application(){
        mySQL = new MySql();
        input = new Input();
        output = new Output(mySQL);
        valid = new Validation(mySQL);
        state = new MyState(input, output, valid, mySQL);
        
    }
    
    
    
    private void friendsProfileMenu(String currentUsername, String anotherUsername) throws SQLException{
        //menu: to see the comments, to add a comment, to return to your profile
        
        output.friendsProfileMenu(anotherUsername);
        int choice=input.getInt();
        switch(choice){
            case(1):{
                output.comments(anotherUsername);
                friendsProfileMenu(currentUsername, anotherUsername);
                break;
            }
            case(2):{
                state.writeComment(currentUsername, anotherUsername);
                friendsProfileMenu(currentUsername, anotherUsername);
                break;
            }
            case(3):{
                break;
            }
            default:{
                friendsProfileMenu(currentUsername, anotherUsername);
            }
        }
        
    }
    
    private void myProfileMenu(String currentUser) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
        output.myProfileMenu(currentUser);
        
        int choice = input.getInt();
        
        switch(choice){
            case(1):{
                String anotherUser = state.searchUser(currentUser);
                
                if (!anotherUser.equals(currentUser)){
                    friendsProfileMenu(currentUser, anotherUser);
                    myProfileMenu(currentUser);
                }
                else{
                    myProfileMenu(currentUser);
                }
                break;
            }
            case(2):{
                state.changeDescription(currentUser);
                myProfileMenu(currentUser);
                break;
            }
            case(3):{
                state.changePassword(currentUser);
                myProfileMenu(currentUser);
                break;
            }
            case(4):{
                output.comments(currentUser);
                mySQL.changeToSeen(currentUser);
                myProfileMenu(currentUser);
                break;
            }
            case(5):{
                break;
            }
            default:{
                myProfileMenu(currentUser);
            }
        }
    }
    
    public void mainMenu() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
        output.mainMenu();
        
        int choice = input.getInt();
        String user;
        
        switch(choice){
            case(1):{
                user=state.login();
                if(user.isEmpty()){
                    output.errorPassword();
                    mainMenu();
                }
                else{
                    myProfileMenu(user);
                    mainMenu();
                }
                break;
            } 
            case(2):{
                user=state.createAccount();
                if(user.isEmpty()){
                    mainMenu();
                }
                else{
                    myProfileMenu(user);
                    mainMenu();
                }
                break;
            }
            case(3):{
                break;
            }
            default:{
                mainMenu();
            }
        }
        
    }
    

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
        Application app = new Application();
        app.mainMenu();
           
    }
    
}