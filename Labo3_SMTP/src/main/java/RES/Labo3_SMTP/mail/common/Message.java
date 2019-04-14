package RES.Labo3_SMTP.mail.common;

import java.util.List;

public class Message {

    private String sender;
    private List<String> receivers;
    private List<String> cc;
    private String message;

    public Message (String sender, List<String> cc, List<String> receivers, String message){
        this.sender = sender;
        this.receivers = receivers;
        this.cc = cc;
        this.message = message;
    }

    



}
