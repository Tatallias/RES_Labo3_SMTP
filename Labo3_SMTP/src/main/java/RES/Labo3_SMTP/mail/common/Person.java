package RES.Labo3_SMTP.mail.common;

public class Person {

    private String address;

    public Person(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o != null && o instanceof Person) {
            Person p = (Person) o;
            if (address == null) {
                return (p.address == null);
            }
            else {
                return address.equals(p.address);
            }
        }

        return false;
    }
    
    @Override
    public int hashCode() {
    	return address.hashCode();
    }
}
