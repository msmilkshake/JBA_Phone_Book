package phonebook.logic;

import phonebook.io.IOReader;
import phonebook.util.Timer;

import java.io.File;
import java.util.List;
import java.util.Map;

public class TextUI {
    
    private final Timer timer = new Timer();
    
    {
        timer.start();
    }
    
    private  Map<String, Contact> contacts;
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
        boolean bubbleSortSuccess = phoneBook.timedBubbleSort(linearSearchTime);
        timer.stop();
        long sortTime = timer.getElapsed();
        if (bubbleSortSuccess) {
            queriesFound = phoneBook.jumpSearch(queries);
        } else {
            queriesFound = phoneBook.linearSearch(queries);
        }
        timer.stop();
        long searchTime = timer.getElapsed() - sortTime;
        System.out.println("Found " + queriesFound + " / " + queries.size() +
                " entries. Time taken: " + timer +
                "\nSorting time: " + Timer.longToString(sortTime) + (bubbleSortSuccess
                ? "" : " - STOPPED, moved to linear search\n" +
                "Searching time: " + Timer.longToString(searchTime)));
    }
}
