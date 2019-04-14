package RES.Labo3_SMTP.mail.common;

import java.util.List;

public class Message {
    private String sender;
    private List<String> recievers;
    private List<String> cc;
    private String message;

    public Message (String sender, List<String> cc, List<String> recievers, String message){
        this.sender = sender;
        this.recievers = recievers;
        this.cc = cc;
        this.message = message;
    }
    
    public String getSender() {
    	return sender;
    }
    
    public List<String> getRecievers() {
    	return recievers;
    }
    
    public List<String> getCC() {
    	return cc;
    }
    
    public String getMessage() {
    	return message;
    }
}
