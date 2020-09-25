package phonebook.util;

import phonebook.logic.Contact;
import phonebook.logic.PhoneBook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        > linearSearchDuration * 2) {
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
        Contact pivot;
        if (r - l > 5) {
            int mid = l + (r - l) / 2;
            List<Contact> pivCandidates = Arrays
                    .asList(list.get(l), list.get(mid), list.get(r));
            int piv = choosePivot(pivCandidates);
            piv = piv == 1 ? l : piv == 2 ? mid : r;
            swap(list, piv, r);
        }
        pivot = list.get(r);
        int partitionI = l;
        for (int i = l; i < r; ++i) {
            if (list.get(i).compareTo(pivot) <= 0) {
                swap(list, i, partitionI++);
            }
        }
        swap(list, partitionI, r);
        return partitionI;
    }
    
    private static int choosePivot(List<Contact> list) {
        Map<Contact, Integer> map = new HashMap<>();
        map.put(list.get(0), 1);
        map.put(list.get(1), 2);
        map.put(list.get(2), 3);
        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < list.size() - 1 - i; ++j) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    swap(list, i, i + 1);
                }
            }
        }
        return map.get(list.get(1));
    }
    
    private static void swap(List<Contact> contacts, int a, int b) {
        Contact temp = contacts.get(a);
        contacts.set(a, contacts.get(b));
        contacts.set(b, temp);
    }
}
