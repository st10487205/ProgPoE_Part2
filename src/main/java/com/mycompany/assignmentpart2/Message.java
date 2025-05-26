/*
        ST10487205
        NTOKOZO NHLAKANIPHO ZULU
        PROG5121 PoE PART 2
        Message Class
 */


package com.mycompany.assignmentpart2;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;

public class Message {
     private String storedMessage;

    public String getStoredMessage() {
        return storedMessage;
    }

    public void setStoredMessage(String storedMessage) {
        this.storedMessage = storedMessage;
    }
        
    public boolean checkMessageID(long MessageID){
        String number = Long.toString(Math.abs(MessageID));
        
        return number.length() <= 10 && !number.matches(".*[A-Za-z].*");
    }
    
    public boolean checkRecipientCell(String phoneNumber){
        return phoneNumber.contains("+27") && phoneNumber.length() == 12 && !(phoneNumber.matches(".*[A-Za-z].*")) ;
    } 
    
    public String createMessageHash(long MessageID, int MessageNumber, String Message){
         String firstWord = "", lastWord = "";
         
         String ID = Long.toString(Math.abs(MessageID));
         
         String[] words = Message.trim().split("\\s+");


        if (words.length > 0) {
            firstWord = words[0];
            lastWord = words[words.length - 1];
        }
         
        String messageHash = ID.substring(0,2) + ":" + MessageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
        
        return messageHash;
    }  
    
    //sentMessage()
    
    public String printMessages(String messageToPrint){    
        return messageToPrint;
    }    
    
    public int returnTotalMessages(int MessageNumber){
        return MessageNumber;
        
    }
    
    public void storeMessage(String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
            System.out.println("Message saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    
        
    public boolean validMessage(String Message){
        return Message.length() <= 250;
    } 

}
