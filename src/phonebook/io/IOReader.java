package phonebook.io;

import phonebook.logic.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOReader {
    public static List<Contact> readDirectory(File file) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.trim().split("(?<=\\d) ");
                contacts.add(new Contact(data[1], data[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    
    public static List<String> readQueries(File file) {
        List<String> queries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                queries.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queries;
    }
}
