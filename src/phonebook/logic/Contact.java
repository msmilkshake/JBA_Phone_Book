package phonebook.logic;

import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private String name;
    private String number;
    
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }
    
    public String getName() {
        return name;
    }
    
    public String getNumber() {
        return number;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) &&
                Objects.equals(number, contact.number);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }
    
    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.name);
    }
}
