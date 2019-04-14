package RES.Labo3_SMTP.config;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import RES.Labo3_SMTP.mail.common.Person;

public class ConfigurationManagerTest {
	private ConfigurationManager configManager;
	
	@Before
    public void setup() {
		configManager = new ConfigurationManager();
    }
	/*
	@Test
	public void testIfMessagesAreLoadedCorrectly() {
		List<String> messages = configManager.getMessages();
		String[] expectedMessages = null;
		
		assertArrayEquals(expectedMessages, messages.toArray());
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testIfPropertiesAreLoadedCorrectly() {
		String serverAddress = configManager.getServerAdress();
		int serverPort = configManager.getServerPort();
		int numberOfGroups = configManager.getNumberOfGroups();
		List<Person> cc = configManager.getCC();
		Person[] expectedCC = {new Person("some-random-dude@mail.com"), new Person("and-another-dude@mail.pl")};
		
		for(Person p : cc) {
			System.out.println(p.getAddress());
		}
		
		assertEquals("smtp.heig-vd.ch", serverAddress);
		assertEquals(25, serverPort);
		assertEquals(3, numberOfGroups);
		assertArrayEquals(expectedCC, cc.toArray());
	}
	
	@Test
	public void testIfVictimsAreLoadedCorrectly() {
		List<Person> victims = configManager.getVictims();
		Person[] someExpectedVictims = {new Person("were.no.strangers.to.love@heig-vd.ch"),
				new Person("never.gonna.let.you.down@heig-vd.ch"),
				new Person("i.just.wanna.tell.you.how.im.feeling@heig-vd.ch"),
				new Person("dont.tell.me.youre.too.blind.to.see@heig-vd.ch")};
	
		for(Person p : someExpectedVictims) {
			assertTrue(victims.contains(p));
		}
	}
}
