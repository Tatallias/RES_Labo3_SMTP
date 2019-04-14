package RES.Labo3_SMTP.mail.prank;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import RES.Labo3_SMTP.config.ConfigurationManager;
import RES.Labo3_SMTP.mail.common.Group;
import RES.Labo3_SMTP.mail.common.Message;
import RES.Labo3_SMTP.mail.common.Person;

public class PrankGenerator {
	private ConfigurationManager configManager;
	private Random rand;
	
	public PrankGenerator(ConfigurationManager configManager) {
		this.configManager = configManager;
		rand = new Random();
	}
	
	public List<Prank> generatePranks() {
		LinkedList<Prank> pranks = new LinkedList<Prank>();
		List<String> messages = configManager.getMessages();
		
		Group[] groups = generateGroups();
		
		for(Group group : groups) {
			List<Person> victims = group.getMembers();
			Person sender = victims.remove(rand.nextInt(victims.size()));
			String message = messages.get(rand.nextInt(messages.size()));
			
			pranks.add(new Prank(sender, victims, configManager.getCC(), message));
		}
		return pranks;
	}
	
	private Group[] generateGroups() {
		Group[] groups = new Group[configManager.getNumberOfGroups()];
		List<Person> victims = configManager.getVictims();
		
		
		
		
		return groups;
	}
}
