package phonebook.logic;

import phonebook.io.IOReader;
import phonebook.util.Search;
import phonebook.util.Sort;
import phonebook.util.Timer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextUI {
    
    private final Timer timer = new Timer();
    private final String dirFile = "test/directory.txt";
    private final String findFile = "test/find.txt";
    
    public void start() {
        long linearSearchTime = linear();
        bubbleJump(linearSearchTime);
        quickBinary();
        hashtableSearch();
    }
    
    private long linear() {
        timer.start();
        PhoneBook phoneBook = new PhoneBook(dirFile);
        List<String> queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (linear search)...");
        int queriesFound = phoneBook.linearSearch(queries);
        timer.stop();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer);
        return timer.getElapsed();
    }
    
    private void bubbleJump(long linearSearchTime) {
        timer.start();
        PhoneBook phoneBook = new PhoneBook(dirFile);
        List<String> queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (bubble sort + jump search)...");
        boolean bubbleSortSuccess =
                Sort.timedBubbleSort(phoneBook, linearSearchTime);
        timer.stop();
        long sortTime = timer.getElapsed();
        timer.start();
        int queriesFound;
        if (bubbleSortSuccess) {
            queriesFound = phoneBook.jumpSearch(queries);
        } else {
            queriesFound = phoneBook.linearSearch(queries);
        }
        timer.stop();
        long searchTime = timer.getElapsed();
        printStats(queriesFound, queries.size(), sortTime, searchTime);
    }
    
    private void quickBinary() {
        timer.start();
        PhoneBook phoneBook = new PhoneBook(dirFile);
        List<String> queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (quick sort + binary search)...");
        Sort.quickSort(phoneBook);
        timer.stop();
        long sortTime = timer.getElapsed();
        timer.start();
        int queriesFound = phoneBook.binarySearch(queries);
        timer.stop();
        long searchTime = timer.getElapsed();
        printStats(queriesFound, queries.size(), sortTime, searchTime);
    }
    
    private void hashtableSearch() {
        timer.start();
        PhoneBook phoneBook = new PhoneBook(dirFile);
        Map<String, Contact> map = new HashMap<>();
        for (Contact contact : phoneBook.getContacts()) {
            map.put(contact.getName(), contact);
        }
        List<String> queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (hash table)...");
        timer.stop();
        long creationTime = timer.getElapsed();
        timer.start();
        int queriesFound = Search.mapSearch(queries, map);
        timer.stop();
        long searchTime = timer.getElapsed();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + Timer.longToString(creationTime + searchTime) +
                "\nCreating time: " + Timer.longToString(creationTime) +
                "\nSearching time: " + Timer.longToString(searchTime));
    }
    
    private void printStats(int queriesFound, int size, long sortTime, long searchTime) {
        System.out.println("Found " + queriesFound + " / " + size +
                " entries. Time taken: " + Timer.longToString(sortTime + searchTime) +
                "\nSorting time: " + Timer.longToString(sortTime) +
                "\nSearching time: " + Timer.longToString(searchTime));
    }
}
