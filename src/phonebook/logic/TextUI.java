package phonebook.logic;

import phonebook.io.IOReader;
import phonebook.util.Search;
import phonebook.util.Sort;
import phonebook.util.Timer;

import java.io.File;
import java.util.List;

public class TextUI {
    
    private final Timer timer = new Timer();
    
    {
        timer.start();
    }
    
    private final PhoneBook phoneBook = new PhoneBook("directory.txt");
    
    public void start() {
        List<String> queries = IOReader.readQueries(new File("find.txt"));
        System.out.println("Start searching (linear search)...");
        int queriesFound = phoneBook.linearSearch(queries);
        timer.stop();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer);
        long linearSearchTime = timer.getElapsed();
        timer.start();
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
                "\nSorting time: " + Timer.longToString(sortTime) + (bubbleSortSuccess
                ? "\n" : " - STOPPED, moved to linear search\n") +
                "Searching time: " + Timer.longToString(searchTime));
    }
    
    public void test() {
        List<String> queries = IOReader.readQueries(new File("find.txt"));
        System.out.println("Start searching (linear search)...");
        int queriesFound = phoneBook.linearSearch(queries);
        timer.stop();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer);
        timer.start();
        System.out.println("Start sorting (quick sort)...");
        Sort.quickSort(phoneBook);
        timer.stop();
        System.out.println("Sorting done in: " + timer);
        timer.start();
        System.out.println("Start searching (jump search)...");
        queriesFound = phoneBook.jumpSearch(queries);
        timer.stop();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer);
        timer.start();
        System.out.println("Start sorting (quick sort) SECOND TIME...");
        Sort.quickSort(phoneBook);
        timer.stop();
        System.out.println("Sorting done in: " + timer);
        timer.start();
        System.out.println("Start searching (jump search) SECOND TIME...");
        queriesFound = phoneBook.jumpSearch(queries);
        timer.stop();
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer);
        System.out.println();
    }
}
