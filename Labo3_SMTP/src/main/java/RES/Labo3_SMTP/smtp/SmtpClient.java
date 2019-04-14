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
        writer.write("EHLO localhost \r\n");
        readLine = reader.readLine();
        LOG.info(readLine);
        if(!readLine.startsWith("250")){
            throw new IOException("error : " + readLine);
        }
        while (readLine.startsWith("250-")){
            readLine = reader.readLine();
            LOG.info(readLine);
        }

        //writer.write("MAIL FROM:" + message);


    }


}
