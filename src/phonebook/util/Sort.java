package phonebook.util;

import phonebook.logic.Contact;
import phonebook.logic.PhoneBook;

import java.util.List;

public class Sort {
    public static boolean timedBubbleSort(PhoneBook pb, long linearSearchDuration) {
        List<Contact> contacts = pb.getContacts();
        Timer timer = new Timer();
        timer.start();
        for (int i = 0; i < contacts.size() - 1; ++i) {
            for (int j = 0; j < contacts.size() - 1 - i; ++j) {
                if (contacts.get(j).compareTo(contacts.get(j + 1)) > 0) {
                    swap(contacts, i, i + 1);
                }
                if (System.currentTimeMillis() - timer.getStartTime()
                        > linearSearchDuration * 10) {
                    return false;
                }
            }
        }
        timer.stop();
        return true;
    }
    
    public static void quickSort(PhoneBook pb) {
        List<Contact> contacts = pb.getContacts();
        quickSort(contacts, 0, contacts.size() - 1);
    }
    
    public static void quickSort(List<Contact> list, int l, int r) {
        if (l < r) {
            int pivot = partition(list, l, r);
            quickSort(list, l, pivot - 1);
            quickSort(list, pivot + 1, r);
        }
    }
    
    public static int partition(List<Contact> list, int l, int r) {
        Contact pivot = list.get(r);
        int partitionI = l;
        for (int i = l; i < r; ++i) {
            if (list.get(i).compareTo(pivot) <= 0) {
                swap(list, i, partitionI++);
            }
        }
        swap(list, partitionI, r);
        return partitionI;
    }
    
    private static void swap(List<Contact> contacts, int a, int b) {
        Contact temp = contacts.get(a);
        contacts.set(a, contacts.get(b));
        contacts.set(b, temp);
    }
}
