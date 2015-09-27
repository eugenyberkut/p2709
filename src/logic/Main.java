package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Yevhen on 27.09.2015.
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Set<String> strings = countWordsInFile("words.txt");
        System.out.println("n = " + strings.size());
        for (String string : strings) {
            System.out.println(string);
        }

        Map<String, Integer> map = countAllWordsInFile("words.txt");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private Map<String, Integer> countAllWordsInFile(String filename) {
        Map<String, Integer> result = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine())!=null) {
                String[] split = line.split("[ ,.!?:;]");
                for (int i=0; i< split.length; i++) {
                    if (!split[i].isEmpty()) {
                        if (!result.containsKey(split[i])) {
                            result.put(split[i], 1);
                        } else {
                            Integer v = result.get(split[i]);
                            result.put(split[i], v+1);
                        }
                    }
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Set<String> countWordsInFile(String filename) {
        Set<String> words = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine())!=null) {
                String[] split = line.split("[ ,.!?:;]");
                for (int i = 0; i < split.length; i++) {
                    String s = split[i];
                    if (!s.isEmpty()) {
                        words.add(s);
                    }
                }
            }
            return words;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
