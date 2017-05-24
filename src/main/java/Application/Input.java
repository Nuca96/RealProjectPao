
package Application;

import java.util.Scanner;

/**
 *
 * @author Irina
 */
public class Input {
    
    private final Scanner sc;
    
    public Input (){
        sc= new Scanner(System.in);
    }
    
    public int getInt(){
        return sc.nextInt();
    }
    
    public String getWord(){
        return sc.next();
    }
    
    public String getText(){
        //read and return a text with one ore more words
        Scanner s= new Scanner(System.in);
        return s.nextLine();
    }
    
}
