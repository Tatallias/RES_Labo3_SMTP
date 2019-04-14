package RES.Labo3_SMTP.mail.common;

import java.util.LinkedList;
import java.util.List;

public class Group {
    private List<Person> members;

    public Group() {
    	members = new LinkedList<Person>();
    }
    
    public Group(List<Person> members){
        this.members = members;
    }

    public List<Person> getMembers() {
        return members;
    }
    
    public void addMember(Person p) {
    	members.add(p);
    }
}
