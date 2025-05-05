/*
        ST10487205
        NTOKOZO NHLAKANIPHO ZULU
        PROG5121 PoE PART 2
        Main Class
 */

package com.mycompany.assignmentpart2;

import javax.swing.JOptionPane;


public class AssignmentPart2 {

    public static void main(String[] args) {
    //Variables
        String name, surname, username, password, phoneNumber, loginUsername, loginPassword;
        
        Login userDetails = new Login();
        
  /*CREATING A PROFILE*/  
    
    //prompt user for name and surname
        name = JOptionPane.showInputDialog("Enter your first name:");
        userDetails.setName(name);
        
        surname = JOptionPane.showInputDialog("Enter your surname:");
        userDetails.setSurname(surname);
        
    //prompt user and check username requirements
        username = JOptionPane.showInputDialog("Enter your username:");
               
        while(!userDetails.validUsername(username)){
            JOptionPane.showMessageDialog(null,"Username is not correctly formatted, please ensure that"
               + " your username contains an underscore and is no more than 5 characters long.");
            username = JOptionPane.showInputDialog("Re-enter username:"); 

        }
        userDetails.setUsername(username);
        
        
    //prompt user and check password requirements
        password = JOptionPane.showInputDialog("Enter your password:");
        

        while(!userDetails.validPasswordComplexity(password)){
           JOptionPane.showMessageDialog(null,"Password is not correctly formatted, please ensure that"
               + " your password includes a capital letter, number, special character and at least 8 characters long.");
           password = JOptionPane.showInputDialog("Re-enter password: ");

        }  
        userDetails.setPassword(password);
        
    //prompt user and check phone number requirements
        phoneNumber = JOptionPane.showInputDialog("Enter your phone number:");
        
        while(!userDetails.validPhoneNumber(phoneNumber)){
            JOptionPane.showMessageDialog(null,"Cellphone number incorrectly formatted or does not contain "
               + "(+27) international code and should not contain any letter(s).");
            phoneNumber = JOptionPane.showInputDialog("Re-enter phone number: ");

        }
        userDetails.setPhoneNumber(phoneNumber);
        
    //Profile successfully created message
      if(userDetails.validPasswordComplexity(password) && userDetails.validPhoneNumber(phoneNumber)){
        JOptionPane.showMessageDialog(null,"\nProfile successfully created! \n===================\nProceed and enter your Login details.");
      }
      
    /*LOGIN*/
    //prompt user for username
      loginUsername = JOptionPane.showInputDialog("Enter your username to login: ");
      
    //checking if the username is correct 
      while(!userDetails.checkUserName(loginUsername)){
        JOptionPane.showMessageDialog(null,"Username incorrect, try again.");      
        loginUsername = JOptionPane.showInputDialog("Re-enter username to login:");
      }
      
    //prompt user for password if username is correct  
      loginPassword = JOptionPane.showInputDialog("Enter your password to login:");

    //checking if the username is correct       
      while(!userDetails.validPasswordComplexity(loginPassword)){
        JOptionPane.showMessageDialog(null,"Password incorrect, try again.");        
        loginPassword = JOptionPane.showInputDialog("Re-enter password to login: ");        
      }
      
    //successfully log in message
    if (userDetails.checkUserName(username) && userDetails.checkPassword(password)){
       JOptionPane.showMessageDialog(null,"Welcome " + userDetails.getName() + ", " + userDetails.getSurname() + " it is great "
            + "to see you again."); 
      }
    
    
    }
}
