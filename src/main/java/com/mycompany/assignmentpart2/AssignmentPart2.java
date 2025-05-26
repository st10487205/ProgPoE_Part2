/*
    ST10487205
    NTOKOZO NHLAKANIPHO ZULU
    PROG5121 PoE PART 2
    Main Class
 */

package com.mycompany.assignmentpart2;

import java.util.Random;
import javax.swing.JOptionPane;


public class AssignmentPart2 {

    public static void main(String[] args) {
    //Variables
        String name, surname, username, password, phoneNumber, loginUsername, loginPassword;
        
        Login userDetails = new Login();
        Message messages = new Message();
       
        
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
      
    //successful log in message
      if (userDetails.checkUserName(username) && userDetails.checkPassword(password)){
         JOptionPane.showMessageDialog(null,"Welcome " + userDetails.getName() + ", " + userDetails.getSurname() + " it is great "
            + "to see you again."); 
      } 
    
   //welcome message
     JOptionPane.showMessageDialog(null,"Welcome to QuickChat");
  
    
   /*SENDING MESSAGES*/    
     boolean quit = false;
     
    while(!quit){     
        int selection = Integer.parseInt(JOptionPane.showInputDialog("Select only one option:\n1) Send messages\n2) Show recently sent messages\n3) Quit"));
        
     switch(selection)
     {
         case 1:
       //Prompts user for the number of messages they want so send
        int numberOfMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
            
        long [] messageID = new long[numberOfMessages];
        String [] messageHash = new String[numberOfMessages];
        String [] message = new String[numberOfMessages];
        String [] recipientPhoneNumber = new String[numberOfMessages];
        int messageSent = 0;
        Random random = new Random();
        
        String build = "****************";
           // 
            for(int z = 0; z < numberOfMessages; z++){
              messageID[z] = random.nextInt(1_000_000_000);
              
              while(!messages.checkMessageID(messageSent)){
                messageID[z] = random.nextInt(1_000_000_000);  
              }
              
              messageSent = z + 1;
              
              recipientPhoneNumber[z] = JOptionPane.showInputDialog("Enter the cellphone number of the recipient");
              while(!messages.checkRecipientCell(recipientPhoneNumber[z])){
                recipientPhoneNumber[z] = JOptionPane.showInputDialog("Recipent number incorrectly formatted.\nRe-enter the cellphone number of the recipient and make sure it contains +27...");
              }
              
              message[z] = JOptionPane.showInputDialog("Type the mesage you want to send.\nMessage should not exceed 250 characters:");
              while(!messages.validMessage(message[z])){
                message[z] = JOptionPane.showInputDialog("Re-type the mesage you want to send.\nMessage should not exceed 250 characters:");  
              }
              
             messageHash[z] = messages.createMessageHash(messageID[z], messageSent, message[z]);
              
              int messageDecision = Integer.parseInt(JOptionPane.showInputDialog("Select one option\n1) Send message\n2) Disregard Message\n3) Store message to send later"));
                switch(messageDecision){
                    case 1:
                        JOptionPane.showMessageDialog(null, "Message sent successfully to " + recipientPhoneNumber[z]);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "The message that was supposed to be sent to " + recipientPhoneNumber[z] + " has been disregarded.");
                        break;
                    case 3:
                        /*JSON File*/
                        messages.setStoredMessage(message[z]);
                        
                        //messages.StoreMessage(messages.getStroredMessage[z]);
                        
                        JOptionPane.showMessageDialog(null,"Message successfully stored to be sent later.\nMessage ID : " + messageID[z]);
                        break;

                   default:
                      JOptionPane.showMessageDialog(null, "No number selected.");
                      break;
                }

              build = build + "\nMessage " + messageSent +  "\nMessage ID : " + messageID[z] + "\nMessage Hash : " +  messageHash[z] + "\nRecipient Phonenumber : "+ recipientPhoneNumber[z] + "\nMessage : "+ message[z] + "\n****************";
                   
            }
                       
             JOptionPane.showMessageDialog(null, messages.printMessages(build) + "\n\nTotal messages sent : " + messages.returnTotalMessages(messageSent));
              break;
         case 2:
             JOptionPane.showMessageDialog(null,"[Coming soon...]\nThis feature will be available in the next update.");
             break;
             
         case 3:
             JOptionPane.showMessageDialog(null,"Thank you for using QuickChat, hope to see you again!");
             quit = true;
             break;
             
         default:
             JOptionPane.showMessageDialog(null, "No number selected.");
             break;
     }
    }
   }
}
