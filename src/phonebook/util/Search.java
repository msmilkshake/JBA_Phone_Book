package phonebook.util;

import phonebook.logic.Contact;
import phonebook.logic.PhoneBook;

import java.util.List;

public class Search {
    public static int linearSearch(PhoneBook phoneBook, String query) {
        int index = 0;
        for (Contact contact : phoneBook.getContacts()) {
            if (query.equals(contact.getName())) {
                return index;
            }
            ++index;
        }
        return -1;
    }
    
    public static int jumpSearch(PhoneBook phoneBook, String query) {
        List<Contact> contacts = phoneBook.getContacts();
        int prevRight = 0;
        int right = 0;
        if (contacts.isEmpty()) {
            return -1;
        }
        if (query.equals(contacts.get(right).getName())) {
            return 0;
        }
        int block = (int) Math.sqrt(contacts.size());
        while (right < contacts.size() - 1) {
            right = Math.min(contacts.size() - 1, right + block);
            if (contacts.get(right).getName()
                    .compareTo(query) >= 0) {
                break;
            }
            prevRight = right;
        }
        if (right == contacts.size() - 1 &&
                query.compareTo(contacts.get(right).getName()) > 0) {
            return -1;
        }
        for (int i = right; i > prevRight; --i) {
            if (query.equals(contacts.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
}
