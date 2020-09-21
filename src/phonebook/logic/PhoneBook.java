package phonebook.logic;

import phonebook.io.IOReader;
import phonebook.util.Search;

import java.io.File;
import java.util.List;

public class PhoneBook {
    private final List<Contact> contacts;
    
    public PhoneBook(String filename) {
        contacts = IOReader.readDirectory(new File(filename));
    }
    
    public List<Contact> getContacts() {
        return contacts;
    }
    
    public int linearSearch(List<String> queries) {
        int foundCount = 0;
        for (String query : queries) {
            if (Search.linearSearch(this, query) > -1) {
                ++foundCount;
            }
        }
        return foundCount;
    }
    
    public int jumpSearch(List<String> queries) {
        int foundCount = 0;
        for (String query : queries) {
            if (Search.jumpSearch(this, query) > -1) {
                ++foundCount;
            }
        }
        return foundCount;
    }
    
    public int binarySearch(List<String> queries) {
        int foundCount = 0;
        for (String query : queries) {
            if (Search.binarySearch(this, query) > -1) {
                ++foundCount;
            }
        }
        return foundCount;
    }
}
