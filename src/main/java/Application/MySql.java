
package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql {
    
    private Connection connection;
    private Statement statement;
    
    public MySql (){
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao", "root", "ciscoconpass");
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertUser(String userName, String firstName, String lastName, String email, String password){
        //insert into users the strings
        //cripted password!!!
    }
    
    public void insertComment (String comment, String sender, String recever){
        //inseret into comments the strings + sysdate (in the field 'data' + a 'no' value (in the field 'seen')
    }
    
    public ResultSet allUserNames() throws SQLException{
        ResultSet res =statement.executeQuery("select username from users;");
        return res;
    }
    
    public ResultSet allEmails() throws SQLException{
        ResultSet res =statement.executeQuery("select email from users;");
        return res;
    }
    
    public ResultSet allInfo(String user) throws SQLException{
        ResultSet res =statement.executeQuery("select * from users where username='" + user +"' ;");
        return res;
    }
    
    public ResultSet allFirstNames() throws SQLException{
        ResultSet res =statement.executeQuery("select firstname from users;");
        return res;
    }
    
    public ResultSet allLastNames() throws SQLException{
        ResultSet res =statement.executeQuery("select lastname from users;");
        return res;
    }
    
    public ResultSet allComments(String user) throws SQLException{
        ResultSet res =statement.executeQuery("select * from comments where recever = '"+ user +"' ;");
        return res;
    }
    
    public void changeToSeen (String user){
        //change the field 'seen' to yes to all the comments for user
    }
    
    public int unseenComments (String user){
        //return how many unseen comments have the user
        return 0;
    }
    
    public void setDescription (String user, String description){
        //change the description for user
    }
    
    public String getPassword (String user){
        //obviously
        //decripted password!!!
        return "";
    }
    
    public void changePassword (String user, String password){
        //cripted!!
    }
    
    public ResultSet searchReults (String word) throws SQLException{
        //select * from users where email=word or lower(firstname)=word...
        //you have more boring details to add in this search
        
        
        
        ResultSet res =statement.executeQuery("select * from users");//this line is to change
        return res;
    }
    
}
