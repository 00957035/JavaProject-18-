package Work;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTxt {
    private static String fileName;
    public WriterTxt(String fileName){
        this.fileName = fileName;
    }
    public static void writeFileText(String txt){ //寫入
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            br.write(txt); //寫入新txt
            br.close();
        }catch(IOException e){ e.printStackTrace(); }
    }
}
