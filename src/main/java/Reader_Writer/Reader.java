package Reader_Writer;

import Word.Word;
import frame.FramePage1;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader {
    private Scanner input;
    private static String fileName; // target file name
    public Reader(String fileName) {
        Reader.fileName = fileName;
    }
    public String[][] readAllWords(int isHide) {
        openFile();
        ArrayList<Word> list = readRecords();
        String[][] listArray = convertArrayList2Array(list, isHide);
        closeFile();
        return listArray;
    }
    public void openFile() {
        try {
            input = new Scanner(Paths.get(fileName));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
    public ArrayList<Word> readRecords() {
        ArrayList<Word> list = new ArrayList<Word>();

        try {
            while (input.hasNext()) // while there is more to read
            {
                Word w = new Word();
                w.setWordEn(input.next());
                w.setWordCn(input.next());
                w.setError(input.nextInt());
                list.add(w);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating.");
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating.");
        }

        return list;
    }
    public String[][] convertArrayList2Array(ArrayList<Word> list, int hide) {
        int size = list.size();
        String[][] listArray = new String[size][];

        for (int i = 0; i < size; i++) {
            String[] record;
            Word account = list.get(i);
            if(hide == 1){
                record = new String[4];
                record[0] = "";
                record[1] = "";
                record[2] = account.getWordCn();
                record[3] = String.format("%d", account.getError());
            }
            else if(hide == 0){
                //System.out.println(account.getWordCn());
                record = new String[3];
                record[0] = account.getWordEn();
                record[1] = account.getWordCn();
                record[2] = String.format("%d", account.getError());
            }
            else{
                record = new String[4];
                record[0] = account.getWordEn();
                record[1] = "";
                record[2] = account.getWordCn();
                record[3] = String.format("%d", account.getError());
            }
            listArray[i] = record;
        }

        return listArray;
    }
    public void closeFile() {
        if (input != null) input.close();
    }
}
