package RES.Labo3_SMTP.smtp;


import RES.Labo3_SMTP.mail.common.Message;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SmtpClient {

    private String serverAddress;
    private int port;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String serverAddress, int port){
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void sendMessage(Message message) throws IOException {

        System.out.println("Sending message");
        socket = new Socket(serverAddress, port);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String readLine = reader.readLine();
        System.out.println(readLine);
        writer.write("EHLO " + serverAddress + "\r\n");
        writer.flush();
        while (readLine.startsWith("250-")){
            readLine = reader.readLine();
            System.out.println(readLine);
        }

        if(serverAddress.contains("mailtrap")){
            writer.write("AUTH LOGIN\r\n");
            writer.flush();
            readLine = reader.readLine();
            System.out.println(readLine);
            writer.write("NTMyZDQzNWFkYTI1MjI=\r\n");
            writer.flush();
            readLine = reader.readLine();
            System.out.println(readLine);
            writer.write("Yjg1ODZhZThhY2M4Njk=\r\n");
            writer.flush();
        }

        readLine = reader.readLine();
        System.out.println(readLine);
        if(!readLine.startsWith("250")){
            throw new IOException("error : " + readLine);
        }
        while (readLine.startsWith("250-")){
            readLine = reader.readLine();
            System.out.println(readLine);
        }

        writer.write("MAIL FROM: <" + message.getSender() + ">\r\n");
        writer.flush();
        readLine = reader.readLine();
        if (!readLine.endsWith("Ok")){
            System.out.println("error : " + readLine);
        }
        System.out.println(readLine);

        for (String reciever : message.getRecievers()) {
            writer.write("RCPT TO: <" + reciever + ">\r\n");
            writer.flush();
            readLine = reader.readLine();
            if (!readLine.endsWith("Ok")) {
                System.out.println("error : " + readLine);
            }
            System.out.println(readLine);
        }

        for (String reciever : message.getCC()){
            writer.write("RCPT TO: <" + reciever + ">\r\n");
            writer.flush();
            readLine = reader.readLine();
            if (!readLine.endsWith("Ok")){
                System.out.println("error : " + readLine);
            }
            System.out.println(readLine);
        }

        writer.write("DATA\r\n");
        writer.flush();
        readLine = reader.readLine();
        System.out.println(readLine);

        writer.write("From: " + message.getSender() + "\r\n");
        writer.flush();

        writer.write("To: " + message.getRecievers().get(0));
        for(int i = 1; i < message.getRecievers().size(); i++){
            writer.write(", " + message.getRecievers().get(i));
        }
        writer.write("\r\n");
        writer.flush();

        writer.write("Cc: " + message.getCC().get(0));
        for(int i = 1; i < message.getCC().size(); i++){
            writer.write(", " + message.getCC().get(i));
        }
        writer.write("\r\n");
        writer.flush();

        readLine = reader.readLine();
        System.out.println(readLine);
        writer.write(message.getMessage() + "\r\n.\r\n");
        writer.flush();

        readLine = reader.readLine();
        System.out.println(readLine);
        writer.write("quit\r\n");
        writer.flush();

        readLine = reader.readLine();
        System.out.println(readLine);
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
    }
}
