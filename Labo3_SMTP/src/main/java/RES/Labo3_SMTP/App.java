package RES.Labo3_SMTP;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import RES.Labo3_SMTP.config.ConfigurationManager;
import RES.Labo3_SMTP.mail.prank.Prank;
import RES.Labo3_SMTP.mail.prank.PrankGenerator;
import RES.Labo3_SMTP.smtp.SmtpClient;

public class App
{
	public static void main(String[] args) throws InterruptedException {
		ConfigurationManager configManager = new ConfigurationManager();
		PrankGenerator pg = new PrankGenerator(configManager);
		List<Prank> pranks = pg.generatePranks();
		SmtpClient sc = new SmtpClient(configManager.getServerAdress(), configManager.getServerPort());
		
		for(Prank p : pranks) {
			try {
				sc.sendMessage(p.createMessage());
				TimeUnit.SECONDS.sleep(5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

}
