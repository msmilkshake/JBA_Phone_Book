package phonebook.logic;

import phonebook.io.IOReader;
import phonebook.util.Sort;
import phonebook.util.Timer;

import java.io.File;
import java.util.List;

public class TextUI {
    
    private final Timer timer = new Timer();
    private final String dirFile = "test/directory.txt";
    private final String findFile = "test/find.txt";
    
    public void start() {
        timer.start();
        PhoneBook phoneBook = new PhoneBook(dirFile);
        List<String> queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (linear search)...");
        int queriesFound = phoneBook.linearSearch(queries);
        timer.stop();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer);
        long linearSearchTime = timer.getElapsed();
        
        timer.start();
        phoneBook = new PhoneBook(dirFile);
        queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (bubble sort + jump search)...");
        boolean bubbleSortSuccess =
                Sort.timedBubbleSort(phoneBook, linearSearchTime);
        timer.stop();
        long sortTime = timer.getElapsed();
        timer.start();
        if (bubbleSortSuccess) {
            queriesFound = phoneBook.jumpSearch(queries);
        } else {
            queriesFound = phoneBook.linearSearch(queries);
        }
        timer.stop();
        long searchTime = timer.getElapsed();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + Timer.longToString(sortTime + searchTime) +
                "\nSorting time: " + Timer.longToString(sortTime) +
                "\nSearching time: " + Timer.longToString(searchTime));
        
        timer.start();
        phoneBook = new PhoneBook(dirFile);
        queries = IOReader.readQueries(new File(findFile));
        System.out.println("Start searching (quick sort + binary search)...");
        Sort.quickSort(phoneBook);
        timer.stop();
        sortTime = timer.getElapsed();
        timer.start();
        queriesFound = phoneBook.binarySearch(queries);
        timer.stop();
        searchTime = timer.getElapsed();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + Timer.longToString(sortTime + searchTime) +
                "\nSorting time: " + Timer.longToString(sortTime) +
                "\nSearching time: " + Timer.longToString(searchTime));
    }
}
