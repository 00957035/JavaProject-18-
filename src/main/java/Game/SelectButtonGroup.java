package Game;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class SelectButtonGroup {
    private ButtonGroup buttonGroup;
    private String select;

    public SelectButtonGroup(ButtonGroup buttonGroup){ //初始化
        this.buttonGroup = buttonGroup;
        selectbuttonGroup(buttonGroup);
    }

    public void selectbuttonGroup(ButtonGroup b){ //判斷ButtoGroup是誰被選中
        for (Enumeration<AbstractButton> buttons = b.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) select = button.getText(); //是 select=Button文字
        }
    }

    public String getSelect(){
        return select;
    }
}
