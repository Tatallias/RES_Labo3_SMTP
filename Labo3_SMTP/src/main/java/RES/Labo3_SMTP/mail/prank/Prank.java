package RES.Labo3_SMTP.mail.prank;

import java.util.LinkedList;
import java.util.List;

import RES.Labo3_SMTP.mail.common.Message;
import RES.Labo3_SMTP.mail.common.Person;

public class Prank {
	private Person sender;
	private LinkedList<Person> recievers;
	private LinkedList<Person> cC;
	private String message;
	
	public Prank(Person sender, List<Person> recievers, List<Person> cC, String message) {
		this.sender = sender;
		this.recievers = new LinkedList<Person>(recievers);
		this.cC = new LinkedList<Person>(cC);
		this.message = message;
	}
	
	public Person getSender() {
		return sender;
	}
	
	public List<Person> getRecievers() {
		return recievers;
	}
	
	public String getMessage() {
		return message;
	}
	
	public List<Person> getCC() {
		return cC;
	}
	
	public Message createMessage() {
		LinkedList<String> recieversAddress = new LinkedList<String>();
		LinkedList<String> ccAddress = new LinkedList<String>();
		
		for(Person reciever : recievers) {
			recieversAddress.add(reciever.getAddress());
		}
		for(Person cc : cC) {
			ccAddress.add(cc.getAddress());
		}
		
		return new Message(sender.getAddress(), ccAddress, recieversAddress, message);
	}
}
