package RES.Labo3_SMTP.smtp;


import RES.Labo3_SMTP.mail.common.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient {




    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String serverAddress;
    private int port;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public SmtpClient(String serverAddress, int port){
        this.serverAddress = serverAddress;
        this.port = port;
    }



    public void sendMessage(Message message) throws IOException{

        LOG.info("Sending message");
        socket = new Socket(serverAddress, port);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String readLine = reader.readLine();
        LOG.info(readLine);
        writer.print("EHLO localhost \r\n");
        readLine = reader.readLine();
        LOG.info(readLine);
        if(!readLine.startsWith("250")){
            throw new IOException("error : " + readLine);
        }
        while (readLine.startsWith("250-")){
            readLine = reader.readLine();
            LOG.info(readLine);
        }

        writer.print("MAIL FROM:" + message.getSender() + "\r\n");
        readLine = reader.readLine();
        if (!readLine.endsWith("accepted")){
            System.out.println("error : " + readLine);
        }
        LOG.info(readLine);

        for (String reciever : message.getRecievers()){
            writer.print("RCPT TO: " + reciever + "\r\n");
            readLine = reader.readLine();
            if (!readLine.endsWith("accepted")){
                System.out.println("error : " + readLine);
            }
            LOG.info(readLine);
        }

        for (String reciever : message.getCC()){
            writer.print("RCPT TO: " + reciever + "\r\n");
            readLine = reader.readLine();
            if (!readLine.endsWith("accepted")){
                System.out.println("error : " + readLine);
            }
            LOG.info(readLine);
        }

        writer.print("DATA\r\n");
        readLine = reader.readLine();
        LOG.info(readLine);
        writer.print("From: " + message.getSender() + "\r\n");
        writer.print("To: " + message.getRecievers().get(0));
        for(int i = 1; i < message.getRecievers().size(); i++){
            writer.print(", " + message.getRecievers().get(i));
        }
        writer.print("\r\n");

        writer.print("Cc: " + message.getCC().get(0));
        for(int i = 1; i < message.getCC().size(); i++){
            writer.print(", " + message.getCC().get(i));
        }
        writer.print("\r\n");

        writer.print(message.getMessage() + "\r\n.\r\n");
        readLine = reader.readLine();
        if(!readLine.contains("accepted")){
            System.out.println("error : " + readLine);
        }
        LOG.info(readLine);
        writer.print("QUIT\r\n");
        reader.close();
        writer.flush();
        writer.close();
        socket.close();
    }
}
