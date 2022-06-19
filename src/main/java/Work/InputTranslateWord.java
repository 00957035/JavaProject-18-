package Work;

import Word.Word;
import translate.Translate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InputTranslateWord extends Component {
    private String english="",  chinese="";
    public boolean addOrNot() throws IOException {
        Icon icon;
        int mType=JOptionPane.INFORMATION_MESSAGE;
        String[] language = {"English", "Chinese"};
        icon = new ImageIcon("img\\l.png");
        String languageChoice = (String)JOptionPane.showInputDialog(this,"Please select an input language：","language", mType, icon, language,"");
        if(languageChoice == null || "".equals(languageChoice)) return false;
        icon = new ImageIcon("img\\t.png");
        String inputWord = (String)JOptionPane.showInputDialog(this, "Enter text to translate：", "Translate", mType, icon, null, "");
        if(inputWord == null) return false;
        icon = new ImageIcon("img\\e.png");
        if(inputWord.equals("")){
            JOptionPane.showMessageDialog(this, "No translation text entered！！！", "Error", JOptionPane.PLAIN_MESSAGE, icon);
            return false;
        }
        else if (languageChoice.equals("English")){
            english = inputWord;
            chinese = Translate.translate("en", "zh_tw", inputWord);
        }
        else if(languageChoice.equals("Chinese")){
            chinese = inputWord;
            english = Translate.translate("zh_tw", "en", inputWord);
        }

        mType = JOptionPane.QUESTION_MESSAGE;
        int oType = JOptionPane.YES_NO_CANCEL_OPTION;
        String[] options={"Add", "Return"};
        icon = new ImageIcon("img\\a.png");
        int opt=JOptionPane.showOptionDialog(this, english+" = "+chinese+"\nAdd to table！or not！?","Add or not", oType, mType,icon, options,"加入");
        if (opt == JOptionPane.YES_OPTION) return true;
        else return false;
    }
    public Word getWord(){
       Word word = new Word(english, chinese, 0);
       return word;
    }

}
