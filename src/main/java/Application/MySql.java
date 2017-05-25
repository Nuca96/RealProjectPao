
package Application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MySql {
    
    private Connection connection;
    private Statement statement;
    private CryptoUtil cript;
    
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
    
    private String criptPassword(String pass) throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException{
        //an algorithm for cripting
        cript= new CryptoUtil();
        String passCript=cript.encrypt(pass);
        
        return passCript;
    }
    
    private String decriptPassword(String pass) throws NoSuchAlgorithmException, 
            InvalidKeySpecException, NoSuchPaddingException, 
            InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            IOException{
        
        //an algorithm fot decripting
        cript = new CryptoUtil();
        String passDescript=cript.decrypt(pass);
        return passDescript;
    }
    
    public void insertUser(String userName, String firstName, String lastName, String email, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
        //insert into users the strings
        //cripted password!!!
        
        String passCript=criptPassword(password);
        String sql="insert into users (firstname, lastname, username, password, email) values ('" + firstName + "', '" +lastName  + "', '" + userName + "', '" +  passCript + "', '" + email+"');";
        
        int n = statement.executeUpdate(sql);
       
    }
    
    public void insertComment (String comment, String sender, String recever) throws SQLException{
        //inseret into comments the strings + sysdate (in the field 'data' + a 'no' value (in the field 'seen')
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/MM/YYYY");  //that is for data!! 
        LocalDate localDate = LocalDate.now();
 
         
        String sql= "insert into comments(sender, recever, comment,seen, date) values ('"+sender+"', '"+recever+"', '"+comment+"', 'no','"+localDate+"');";
        int n= statement.executeUpdate(sql);
 
    }
    
    public ResultSet allUserNames() throws SQLException{
        ResultSet res =statement.executeQuery("select username from users;");
        return res;
    }
    
    public String getPassword(String user) throws SQLException, 
            NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException, 
            InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, 
            BadPaddingException,
            IOException{
        ResultSet res =statement.executeQuery("select password from users where username='"+user+"';");
        res.next();
        String password=res.getString("password");
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
    
    public void changeToSeen (String user) throws SQLException{
        //change the field 'seen' to yes to all the comments for user
        String sql;
        
        sql="update comments set seen = 'yes' where recever = '"+user+"';";
        int n = statement.executeUpdate(sql);
        
    }
    
    public int unseenComments (String user) throws SQLException{
        //return how many unseen comments have the user
        int count =0;
        String sql ="select count(*) from comments where seen= 'no' && recever='"+user+"';";
        
        ResultSet res = statement.executeQuery(sql);
        res.next();
        count=res.getInt(1);
      
        return count;
    }
    
    public void setDescription (String user, String description) throws SQLException{
        //change the description for user
        
        String sql="update users set description = '"+ description + "' where username = '" + user+"';";
        statement.executeUpdate(sql);
        
    }
    
    public void changePassword (String user, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SQLException{
        //cripted!!
        
        String passCript=cript.encrypt(password);
        String sql="update users set password = '"+passCript+"' where username = '"+user+"';";
        statement.executeUpdate(sql);
    }
    
    public ResultSet searchResults (String word) throws SQLException{
        //select * from users where email=word or lower(firstname)=word...
        //you have more boring details to add in this search
        word = word.toLowerCase();
        ResultSet res=statement.executeQuery("select * from users where lower(username) like '%"+word+"%' or lower(firstname) like '%"+word+"%' or lower(lastname) like '%"+word+"%';");
        
        return res;
    }
    
}
