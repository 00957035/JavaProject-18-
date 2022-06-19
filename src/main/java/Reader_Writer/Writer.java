package Reader_Writer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class Writer {
    private static Formatter output; // outputs text to a file
    private String fileName; // target file name

    public Writer(String fileName) {
        this.fileName = fileName;
    }

    public void addWord(String wordEN, String wordCn, int n) {
        //System.out.println(fileName);
        openFile();
        addRecord(wordEN, wordCn, n);
        closeFile();
    }

    public void addQuestions(String line){
        openFile();
        addRecord(line);
        closeFile();
    }

    public void addRecord(String line) {
        try {
            output.format("%s\n", line);
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        }
    }

    public void openFile() {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            output = new Formatter(fw);
        } catch (SecurityException securityException) {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1); // terminate the program
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1); // terminate the program
        } catch (IOException e) {
            System.err.println("I/O error. Terminating.");
            System.exit(1); // terminate the program
        }
    }

    public void addRecord(String wordEn, String wordCn, int n) {
        try {
            output.format("%s\n%s\n%d\n", wordEn, wordCn,  0);
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        }
    }
    public static void closeFile() {
        if (output != null) output.close();
    }
}
