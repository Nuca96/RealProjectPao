/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        return "";
    }
    
}
