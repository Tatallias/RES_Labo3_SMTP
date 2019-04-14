package RES.Labo3_SMTP;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) {

        LinkedList<String> victims = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("config"));//nom fichier a définir
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String s : values){
                    victims.add(s);
                }
            }
        }
        catch (Exception ex){
            System.out.println("error");
        }


    }

}
