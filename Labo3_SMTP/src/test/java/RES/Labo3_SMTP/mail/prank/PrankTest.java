package RES.Labo3_SMTP.mail.prank;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

import RES.Labo3_SMTP.mail.common.Message;
import RES.Labo3_SMTP.mail.common.Person;

public class PrankTest{
	@Test
	public void aPrankShouldGenerateTheCorrectMessage() {
		LinkedList<Person>victims = new LinkedList<Person>();
		victims.add(new Person("foo@bar.com"));
		victims.add(new Person("bar@foo.com"));
		LinkedList<Person> cc = new LinkedList<Person>();
		cc.add(new Person("someperson@gmail.org"));
		Person sender = new Person("hello@world.fr");
		String message = "Greetings";
		String[] expectedRecievers = {"foo@bar.com", "bar@foo.com"};
		String[] expectedCC = {"someperson@gmail.org"};
		
		
		Prank p = new Prank(sender, victims, cc, message);
		Message m = p.createMessage();
		assertEquals(message, m.getMessage());
		assertEquals(sender.getAddress(), m.getSender());
		assertArrayEquals(expectedRecievers, m.getRecievers().toArray());
		assertArrayEquals(expectedCC, m.getCC().toArray());
	}
}
