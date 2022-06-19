package Work;

import Reader_Writer.Reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shuffle {
    private static ReaderTxt readerTxt;
    private static WriterTxt writerTxt;
    private static String fileName;
    public Shuffle(int wordLength, String fileName) throws IOException {
        this.fileName = fileName;
        readerTxt = new ReaderTxt(fileName);
        writerTxt = new WriterTxt(fileName);
        int idx=-1;
        int[] array = new int[wordLength];
        List<Integer> intList = new ArrayList<>();
        Random r = new Random();
        while(intList.size() < wordLength){
            int randomNumber = r.nextInt(wordLength);
            if(!intList.contains(randomNumber)){
                intList.add(randomNumber);
                array[++idx] = randomNumber;
            }
        }
        //readLineVarFile((0+1)+(2*0), (array[0]+1)+(2*array[0]));
        for(int i=0; i<wordLength; i++){
            System.out.println(i + " " + array[i]);
            readLineVarFile((i+1)+(2*i), (array[i]+1)+(2*array[i]));
        }
    }
    public static void changeTxtWord(String oldWord, String newWord){
        String txt = readerTxt.getFileText(); //取得檔案內容
        txt = txt.replaceFirst(newWord, oldWord); //換掉某個字
        writerTxt.writeFileText(txt); //將換過的檔案內容寫回去

        txt = readerTxt.getFileText(); //取得檔案內容
        txt = txt.replaceFirst(oldWord, newWord); //換掉某個字
        writerTxt.writeFileText(txt); //將換過的檔案內容寫回去
    }
    public static void readLineVarFile(int lineNumber01, int lineNumber02) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用緩衝區的方法將資料讀入到緩衝區中
        String line = reader.readLine(); //定義行數
        int num=0;
        boolean isFind=false, isOldWordFind=false;
        String oldWord="", newWord="";
        while (line != null){     //當行數不為空時，輸出該行內容及字元數
            if (lineNumber01 == ++num){
                isFind = true; //找到
                System.out.println("第" + lineNumber01 + "行: " + line); //印出要交換行數 + 內容
            }
            if(lineNumber02 == num){
                isFind = true; //找到
                System.out.println("第" + lineNumber02 + "行: " + line); //印出要交換行數 + 內容
            }
            if(isFind && !isOldWordFind){ //找到 && oldWord還沒有
                isOldWordFind = true;
                oldWord = line + "\n";
                line = reader.readLine();
                oldWord += (line + "\n");
                line = reader.readLine();
                oldWord += (line + "\n");
                num += 2;
            }
            else if(isFind){ //找到 && oldWord已找到
                newWord = line + "\n";
                line = reader.readLine();
                newWord += (line + "\n");
                line = reader.readLine();
                newWord += (line + "\n");
                break;
            }
            isFind = false;
            line = reader.readLine();
        }
        //System.out.print(oldWord + newWord);
        changeTxtWord(oldWord, newWord);
        reader.close();
    }
}
