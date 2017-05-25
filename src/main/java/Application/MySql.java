
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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao", "root", "ciscoconpass");
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String criptPassword(String pass){
        //an algorithm for cripting
        return pass;
    }
    
    private String decriptPassword(String pass){
        //an algorithm fot decripting
        return pass;
    }
    
    public void insertUser(String userName, String firstName, String lastName, String email, String password){
        //insert into users the strings
        //cripted password!!!
        
        password=criptPassword(password);
        String sql="insert into users (firstname, lastname, username, password, email) values ('" + firstName + "', '" +lastName  + "', '" + userName + "', '" +  password + "', '" + email+"');";
        try {
                    int n = statement.executeUpdate(sql);
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertComment (String comment, String sender, String recever){
        //inseret into comments the strings + sysdate (in the field 'data' + a 'no' value (in the field 'seen')
        
        String sql= "insert into comments(sender, recever, comment,seen, date) values ('"+sender+"', '"+recever+"', '"+comment+"', 'no', to_char(sysdate,'DD-MM-YYYY');";
        try{
            
            int n= statement.executeUpdate(sql);
            
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet allUserNames() throws SQLException{
        ResultSet res =statement.executeQuery("select username from users;");
        return res;
    }
    
    public String getPassword(String user) throws SQLException{
        ResultSet res =statement.executeQuery("select password from users where username='"+user+"';");
        res.next();
        String password=res.getString(1);
        password=decriptPassword(password);
        return password;
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
    
    public ResultSet allData(String user) throws SQLException{
        ResultSet res =statement.executeQuery("select * from users where username = '"+ user +"' ;");
        return res;
    }
    
    public void changeToSeen (String user){
        //change the field 'seen' to yes to all the comments for user
        String sql;
        sql="update comments set seen = 'yes' where recever = '"+user+"';";
                
        try{
            int n = statement.executeUpdate(sql);
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int unseenComments (String user){
        //return how many unseen comments have the user
        int count =0;
        String sql ="select count(*) from comments where seen= 'no' && recever='"+user+"';";
        
        try{
            ResultSet res = statement.executeQuery(sql);
            res.next();
            count=res.getInt(1);
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public void setDescription (String user, String description){
        //change the description for user
        
        String sql="update users set description = '"+ description + "' where username = '" + user+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changePassword (String user, String password){
        //cripted!!
    }
    
    public ResultSet searchResults (String word) throws SQLException{
        //select * from users where email=word or lower(firstname)=word...
        //you have more boring details to add in this search
        word = word.toLowerCase();
        ResultSet res=statement.executeQuery("select * from users where lower(username) like '%"+word+"%' or lower(firstname) like '%"+word+"%' or lower(lastname) like '%"+word+"%';");
        
        return res;
    }
    
}
