package Work;

import frame.FramePage1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class VisitWebsite extends Component {
    public VisitWebsite(){
        String[] options={"Sure", "Not yet"};
        Icon icon = new ImageIcon("img\\w.png");
        int mType = JOptionPane.QUESTION_MESSAGE;
        int oType = JOptionPane.YES_NO_CANCEL_OPTION;
        int opt=JOptionPane.showOptionDialog(this, "Go to the website！","Go to the website", oType, mType,icon, options,"Sure");
        if (opt == JOptionPane.YES_OPTION){
            try {
                Desktop.getDesktop().browse(new URI("https://sites.google.com/dwhs.tn.edu.tw/andydwhs/%E6%AD%B7%E5%B1%86%E5%A4%A7%E5%AD%B8%E8%81%AF%E8%80%83%E7%B7%9A%E4%B8%8A%E6%B8%AC%E9%A9%97/%E6%AD%B7%E5%B1%86%E5%A4%A7%E5%AD%B8%E5%AD%B8%E6%B8%AC%E7%B7%9A%E4%B8%8A%E6%B8%AC%E9%A9%97"));
            } catch (IOException | URISyntaxException ex) {
                System.err.println(ex);
            }
        }
    }
}
