package Work;

import java.io.*;

public class ReaderTxt {
    private static String fileName;
    public ReaderTxt(String fileName){
        this.fileName = fileName;
    }
    public static String getFileText(){ //取得文件資料
        StringBuilder strBuf = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream(fileName)));
            while(br.ready()) { //通知是否已準備好被讀取
                String brStr = br.readLine() + "\n"; //讀一行 + 換行符號
                strBuf.append(brStr); //有讀到
            }
            br.close();
        }catch(IOException e){ e.printStackTrace(); }
        return strBuf.toString(); //回傳文件內容
    }
}
