package phonebook.logic;

import phonebook.io.IOReader;
import phonebook.util.Search;
import phonebook.util.Timer;

import java.io.File;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;
    
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
    
    public boolean timedBubbleSort(long linearSearchDuration) {
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < contacts.size() - 1; ++i) {
            for (int j = 0; j < contacts.size() - 1 - i; ++j) {
                if (contacts.get(j).compareTo(contacts.get(j + 1)) > 0) {
                    Contact tmp = contacts.get(j);
                    contacts.set(j, contacts.get(j + 1));
                    contacts.set(j + 1, tmp);
                }
                if (System.currentTimeMillis() - timer.getStartTime()
                        > linearSearchDuration * 10) {
                    return false;
                }
            }
        }
        return true;
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
}
