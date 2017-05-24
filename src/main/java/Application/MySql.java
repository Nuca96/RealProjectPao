
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pao", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertUser(String userName, String firstName, String lastName, String email, String password){
        //insert into users the strings
        //cripted password!!!
        
        String sql="insert into users (username, email, lastname, firstname, password) values ('" + firstName + "', '" +lastName  + "', '" + userName + "', '" +  password + "', '" + email+"');";
        try {
                    int n = statement.executeUpdate(sql);
                    if (n==1){
                        System.out.println("Accound created ");
                    }
                    else{
                        System.out.println("Error!");
                    }
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertComment (String comment, String sender, String recever){
        //inseret into comments the strings + sysdate (in the field 'data' + a 'no' value (in the field 'seen')
        
        String sql= "insert into comments(sender, recever, comment,seen) values ('"+sender+"', '"+recever+"', '"+comment+"', '"+"no"+"');";
        try{
            
            int n= statement.executeUpdate(sql);
            if(n==1){
                System.out.println("Comment was inserted ");
            }
            else{
                System.out.println("Error!");
            }
            
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet allUserNames() throws SQLException{
        ResultSet res =statement.executeQuery("select username from users;");
        return res;
    }
    
    public ResultSet allUserNamesPassword() throws SQLException{
        ResultSet res =statement.executeQuery("select username, password from users;");
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
    
    public ResultSet allData(String user) throws SQLException{
        ResultSet res =statement.executeQuery("select * from comments where username = '"+ user +"' ;");
        return res;
    }
    
    public void changeToSeen (String user){
        //change the field 'seen' to yes to all the comments for user
        String sql;
        sql="update comments set seen = 'yes' where seen = 'no';";
                
        try{
            int n = statement.executeUpdate(sql);
            if (n!=1){
                System.out.println("Error!");
            }
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int unseenComments (String user){
        //return how many unseen comments have the user
        int count =0;
        String sql ="select count(*) from comments where seen= 'no'";
        
        try{
            ResultSet res = statement.executeQuery(sql);
       
            while(res.next()){
                count++;
            }
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public void setDescription (String user, String description){
        //change the description for user
        
        String sql="update users set description = '"+ description + "' where username = '" + user+"';";
        try {
            int n = statement.executeUpdate(sql);
            if (n==1){
                System.out.println("Description changed");
            }
            else{
                System.out.println("Error!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getPassword (String user){
        //obviously
        //decripted password!!!
        
        String sql="select password from users where username='"+user+"';";
        String password="";
        
        try{
            ResultSet res=statement.executeQuery(sql);
            password=res.getString("password");
        }catch(SQLException ex){
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return password;
    }
    
    public void changePassword (String user, String password){
        //cripted!!
    }
    
    public ResultSet searchReults (String word) throws SQLException{
        //select * from users where email=word or lower(firstname)=word...
        //you have more boring details to add in this search
        ResultSet res=statement.executeQuery("select * from users where username like '"+word+"%' or firstname like "+word+"%' or lastnamelike "+word+"%'");
        
        return res;
    }
    
}
