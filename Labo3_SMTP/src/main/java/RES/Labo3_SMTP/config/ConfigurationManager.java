package RES.Labo3_SMTP.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import RES.Labo3_SMTP.mail.common.Person;

public class ConfigurationManager {
	private String serverAddress;
	private int serverPort;
	private int numberOfGroups;
	LinkedList<Person> victims;
	LinkedList<String> messages;
	LinkedList<Person> cc;
	
	public ConfigurationManager() {
		victims = new LinkedList<Person>();
		messages = new LinkedList<String>();
		cc = new LinkedList<Person>();
		try {
			loadMessages();
			loadVictims();
			loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadMessages() throws IOException {
		FileReader file = new FileReader("./config/messages.utf8");
		BufferedReader buffRead = new BufferedReader(file);
		
		String message = "";
		String line = null;

		while ((line = buffRead.readLine()) != null)
		{    
		    if(line == "==") {
		    	messages.add(message);
		    	message = "";
		    } else {
		    	message.concat("\n" + line);
		    }
		}
		buffRead.close();
	}
	
	private void loadVictims() throws IOException {
		FileReader file = new FileReader("./config/victims.utf8");
		BufferedReader buffRead = new BufferedReader(file);
		
		String line = null;

		while ((line = buffRead.readLine()) != null)
		{    
		    victims.add(new Person(line));
		}
		buffRead.close();
	}
	
	private void loadProperties() throws IOException {
		FileReader file = new FileReader("./config/properties.utf8");
		BufferedReader buffRead = new BufferedReader(file);
		
		String line = null;

		int count = 0; // to locate ourself in the parsing
		while ((line = buffRead.readLine()) != null || count < 4)
		{    
			String[] array = line.split("=");
			
			switch(count)  {
				case 0:
					serverAddress = array[1];
					break;
				case 1:
					serverPort = Integer.parseInt(array[1]);
					break;
				case 2:
					numberOfGroups = Integer.parseInt(array[1]);
					break;
				case 3:
					 String[] ccs = array[1].split(" ");
					 for(String s : ccs) {
						 cc.add(new Person(s));
					 }
					break;
				default:
					break;
			}
			count++;
		}
		buffRead.close();
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public String getServerAdress() {
		return serverAddress;
	}
	
	public int getNumberOfGroups() {
		return numberOfGroups;
	}

	public List<Person> getCC() {
		return cc;
	}

	public List<String> getMessages() {
		return messages;
	}

	public List<Person> getVictims() {
		return victims;
	}

}
