package phonebook.util;

import phonebook.logic.Contact;
import phonebook.logic.PhoneBook;

import java.util.List;
import java.util.Map;

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
    
    public static int binarySearch(PhoneBook phoneBook, String query) {
        return binarySearch(phoneBook.getContacts(), query,
                0, phoneBook.getContacts().size() - 1);
    }
    
    public static int binarySearch(List<Contact> list, String query,
            int left, int right) {
        if (left <= right) {
            int middle = left + (right - left) / 2;
            String queryName = list.get(middle).getName();
            if (queryName.equals(query)) {
                return middle;
            }
            if (queryName.compareTo(query) > 0) {
                return binarySearch(list, query, left, middle - 1);
            } else {
                return binarySearch(list, query, middle + 1, right);
            }
        }
        return -1;
    }
    
    public static int mapSearch(List<String> queries, Map<String, Contact> map) {
        int queriesFound = 0;
        for (String query : queries) {
            if (map.get(query) != null) {
                ++queriesFound;
            }
        }
        return queriesFound;
    }
}
