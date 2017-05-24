package Application;

/**
 *
 * @author Irina
 */
public class Application {
    
    MySql mySQL;
    Input input;
    Output output;
    Validation valid;
    
    public Application(){
        mySQL = new MySql();
        input = new Input();
        output = new Output(mySQL);
        valid = new Validation(mySQL);
    }
    
    private void writeCommentState(String currentUsername, String anotherUsername){
        //currentUsername wants to write a comment to anotherUsername
        //you don't have to call another state or menu method
    }
    
    private void friendsProfileMenu(String currentUsername, String anotherUsername){
        //menu: to see the comments, to add a comment, to return to your profile
        
        output.friendsProfileMenu();
        int choice=input.getInt();
        switch(choice){
            case(1):{
                output.comments(anotherUsername);
            }
            case(2):{
                writeCommentState(currentUsername, anotherUsername);
            }
            case(3):{
                break;
            }
            default:{
                friendsProfileMenu(currentUsername, anotherUsername);
            }
        }
    }
    
    private String searchUserState (){
        //returns a username 
        //you could create and use another method because this one is kind of complicated
        return "";
    }
    
    private void changeDescriptionState(){
        //you mustn't call another menu or state function
    }
    
    private void changePasswordState(){
        //the same comment
    }
    
    private void myProfileMenu(String currentUser){
        output.myProfileMenu();
        
        int choice = input.getInt();
        
        switch(choice){
            case(1):{
                String anotherUser = searchUserState();
                
                if (!anotherUser.equals(currentUser)){
                    friendsProfileMenu(currentUser, anotherUser);
                }
            }
            case(2):{
                changeDescriptionState();
            }
            case(3):{
                changePasswordState();
            }
            case(4):{
                output.comments(currentUser);
                mySQL.changeToSeen(currentUser);
            }
            case(5):{
                break;
            }
            default:{
                myProfileMenu(currentUser);
            }
        }
    }
    
    private String loginState(){
        output.loginState();
        
        String username = input.getWord();
        String password = input.getWord();
        
        if (valid.password(password, username)){
            return username;
        }
        
        return "";
    }
    
    private String createAccountState(){
        
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
    
    public void mainMenu(){
        output.mainMenu();
        int choice = input.getInt();
        String user;
        
        switch(choice){
            case(1):{
                user=loginState();
                if(!user.isEmpty())
                    myProfileMenu(user);
            } 
            case(2):{
                user=createAccountState();
                if(!user.isEmpty())
                    myProfileMenu(user);
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
        app.mainMenu();
    }
    
}