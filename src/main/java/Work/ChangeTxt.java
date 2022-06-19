package Work;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangeTxt {
    private static ReaderTxt readerTxt;
    private static WriterTxt writerTxt;
    private static String fileName;
    public ChangeTxt(String fileName){
        this.fileName = fileName;
        readerTxt = new ReaderTxt(fileName);
        writerTxt = new WriterTxt(fileName);
    }
    public static void changeErrorTime(String oldString, String newString){
        String txt = readerTxt.getFileText(); //取得檔案內容
        txt = txt.replaceFirst(oldString, newString); //newString 取代 oldString
        writerTxt.writeFileText(txt); //將換過的檔案內容寫回去
    }
    public static void readLineVarFileWord(String word, String errorTime) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用緩衝區的方法將資料讀入到緩衝區中
        String line = reader.readLine(); //定義行數
        int num=0;
        String oldString="", newString="";
        while (line != null){     //當行數不為空時，輸出該行內容及字元數
            if(line.equals(word)){ //找到文那行
                oldString = line + "\n";
                newString = line + "\n";
                line = reader.readLine();
                oldString += (line + "\n");
                newString += (line + "\n");
                line = reader.readLine();
                oldString += (line + "\n");
                newString += (errorTime + "\n");
                //oldString = 英文 + 中文 + 原errorTime
                //newString = 英文 + 中文 + errorTime+1
                break;
            }
            line = reader.readLine();
        }
        changeErrorTime(oldString, newString);
        reader.close();
    }

    public static void readLineVarFileString(String word, String errorTime) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用緩衝區的方法將資料讀入到緩衝區中
        String line = reader.readLine(); //定義行數
        int num=0;
        String oldString="", newString="";
        while (line != null){     //當行數不為空時，輸出該行內容及字元數
            if(line.equals(word)){ //找到文那行
                oldString = line + "\n";
                newString = line + "\n";
                line = reader.readLine();
                oldString += (line + "\n");
                newString += (errorTime + "\n");
                break;
            }
            line = reader.readLine();
        }
        changeErrorTime(oldString, newString);
        reader.close();
    }
}
