package Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private void myProfileMenu(String currentUser) throws SQLException{
        output.myProfileMenu(currentUser);
        
        int choice = input.getInt();
        
        switch(choice){
            case(1):{
                String anotherUser = state.searchUser(currentUser);
                
                if (!anotherUser.equals(currentUser)){
                    friendsProfileMenu(currentUser, anotherUser);
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
    
    public void mainMenu() throws SQLException{
        output.mainMenu();
        int choice = input.getInt();
        String user;
        
        switch(choice){
            case(1):{
                user=state.login();
                if(user.isEmpty()){
                    mainMenu();
                }
                else{
                    myProfileMenu(user);
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
    
    public static void main(String[] args) {
        Application app = new Application();
        try {
            app.mainMenu();
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}